package hhs.server.api.service.impl;

import hhs.server.api.service.LikeService;
import hhs.server.common.exceptions.ExceptionCode;
import hhs.server.common.exceptions.exception.NotFoundException;
import hhs.server.domain.model.dto.request.likes.BaseLikeRequest;
import hhs.server.domain.model.dto.request.likes.CommentLikeRequest;
import hhs.server.domain.model.dto.request.likes.ProjectLikeRequest;
import hhs.server.domain.model.dto.response.ResultResponse;
import hhs.server.domain.model.type.LikeType;
import hhs.server.domain.model.type.ResultCode;
import hhs.server.domain.persistence.CommentLikes;
import hhs.server.domain.persistence.Comments;
import hhs.server.domain.persistence.ProjectLikes;
import hhs.server.domain.persistence.Projects;
import hhs.server.domain.repository.jpa.CommentsLikeRepository;
import hhs.server.domain.repository.jpa.CommentsRepository;
import hhs.server.domain.repository.jpa.ProjectRepository;
import hhs.server.domain.repository.jpa.ProjectsLikeRepository;
import hhs.server.domain.util.UpdateManager;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserLikeService implements LikeService {

  private final CommentsLikeRepository commentsLikeRepository;
  private final ProjectsLikeRepository projectsLikeRepository;
  private final CommentsRepository commentsRepository;
  private final ProjectRepository projectsRepository;
  private final RedisTemplate<String, String> redisTemplate;

  private final static String LIKE_KEY_PREFIX = "like:";
  private final static String COMMENT_LIKE_KEY = "comment:";
  private final static String PROJECT_LIKE_KEY = "project:";

  @Override
  public ResultResponse like(BaseLikeRequest baseLikeRequest) {
    String key;
    LikeType likeType = baseLikeRequest.getLikeType();
    String userId = baseLikeRequest.getUserId().toString();

    if (likeType.equals(LikeType.PROJECT)) {
      key = LIKE_KEY_PREFIX + PROJECT_LIKE_KEY
          + ((ProjectLikeRequest) baseLikeRequest).getProjectId();
    } else {
      key = LIKE_KEY_PREFIX + COMMENT_LIKE_KEY
          + ((CommentLikeRequest) baseLikeRequest).getCommentId();
    }

    return toggleLike(key, userId);
  }

  private ResultResponse toggleLike(String key, String userId) {

    boolean isLiked = Boolean.TRUE.equals(redisTemplate.opsForSet().isMember(key, userId));

    Long userIdLong = Long.parseLong(userId);

    if (isLiked) {

      redisTemplate.opsForSet().remove(key, userId);

      //////임시
      long targetId = Long.parseLong(key.split(":")[2]);

      if (isProjectLike(key)) {
        projectsLikeRepository.deleteByProjectIdAndUserId(targetId, userIdLong);
      } else {
        commentsLikeRepository.deleteByCommentIdAndUserId(targetId, userIdLong);
      }

      return ResultResponse.of(isProjectLike(key) ? ResultCode.PROJECT_DISLIKE_SUCCESS
          : ResultCode.COMMENT_DISLIKE_SUCCESS);

    } else {
      redisTemplate.opsForSet().add(key, userId);
      return ResultResponse.of(
          isProjectLike(key) ? ResultCode.PROJECT_LIKE_SUCCESS : ResultCode.COMMENT_LIKE_SUCCESS);
    }
  }


  @Scheduled(cron = "0 0/10 * * * *")
  @Transactional
  public void saveLikeCount() {
    updateLikes(LikeType.PROJECT);
    updateLikes(LikeType.COMMENT);
  }

  @Transactional
  public void updateLikes(LikeType likeType) {

    // key scan 개선
    // scan 방식에서 Project id를 추출하여 해당 Project Entity를 조회하고 좋아요 수를 업데이트
    List<Long> targetsId = likeType == LikeType.PROJECT ? projectsRepository.findAllIdWithDetail() : commentsRepository.findAllIdWithDetail();
    for (Long targetId : targetsId) {
      String key = LIKE_KEY_PREFIX + PROJECT_LIKE_KEY + targetId;
      updateLikeCount(key,targetId);
    }
  }

  @Transactional
  public void updateLikeCount(String fullKey,Long targetId) {
    // Key에 따른 LikeType 설정
    // like:project:1 -> LikeType.PROJECT, like:comment:1 -> LikeType.COMMENT
    LikeType likeType = fullKey.contains(PROJECT_LIKE_KEY) ? LikeType.PROJECT : LikeType.COMMENT;

    // Redis에서 해당 키 값의 member 수 조회
    // like:project:1 -> ProjectId 1의 좋아요 수
    Long likeCount = getLikeCount(fullKey);

    // 프로젝트 엔티티 조회 후 좋아요 수 업데이트
    if (likeType == LikeType.PROJECT) {
      Projects project = projectsRepository.findById(targetId).orElseThrow(
          () -> new NotFoundException(ExceptionCode.PROJECT_NOT_FOUND)
      );

      UpdateManager.updateProjectLikeCount(project, likeCount);

      getLikeUser(fullKey).forEach(
          userId -> updateProjectLikeTable(targetId, Long.parseLong(userId)));
      // 업데이트 된 Project Entity 저장
      projectsRepository.saveAndFlush(project);
    } else {
      Comments comments = commentsRepository.findById(targetId).orElseThrow(
          () -> new NotFoundException(ExceptionCode.COMMENTS_NOT_FOUND)
      );
      UpdateManager.updateCommentLikeCount(comments, likeCount);

      getLikeUser(fullKey).forEach(
          userId -> updateCommentLikeTable(targetId, Long.parseLong(userId)));
      // 업데이트 된 Comment Entity 저장
      commentsRepository.saveAndFlush(comments);
    }
  }

  @Transactional
  public void updateProjectLikeTable(Long projectId, Long userId) {
    // redis에 좋아요가 기록되어 있지만, DB에는 좋아요가 기록되어 있지 않을 경우
    if (!projectsLikeRepository.existsByProjectIdAndUserId(projectId, userId)) {
      ProjectLikes newProjectLike = ProjectLikes.builder()
          .projectId(projectId)
          .userId(userId)
          .build();
      projectsLikeRepository.saveAndFlush(newProjectLike);
    }
  }

  @Transactional
  public void updateCommentLikeTable(Long commentId, Long userId) {
    if (!commentsLikeRepository.existsByCommentIdAndUserId(commentId, userId)) {
      CommentLikes newCommentLike = CommentLikes.builder()
          .commentId(commentId)
          .userId(userId)
          .build();
      commentsLikeRepository.saveAndFlush(newCommentLike);
    }
  }

  private Long getLikeCount(String key) {
    if(Boolean.TRUE.equals(redisTemplate.hasKey(key))){
      return redisTemplate.opsForSet().size(key);
    }
    return 0L;
  }

  private Set<String> getLikeUser(String key) {
    return redisTemplate.opsForSet().members(key);
  }

  private boolean isProjectLike(String key) {
    return key.contains(PROJECT_LIKE_KEY);
  }
}
