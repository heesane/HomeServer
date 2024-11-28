package hhs.server.api.service.impl;

import hhs.server.api.service.CommentsService;
import hhs.server.domain.aop.badge.BadgeCheck;
import hhs.server.domain.exception.ExceptionCode;
import hhs.server.common.exceptions.exception.NotFoundException;
import hhs.server.common.exceptions.exception.UnmatchedUserException;
import hhs.server.domain.model.dto.request.comments.DeleteCommentRequest;
import hhs.server.domain.model.dto.request.comments.UpdateCommentRequest;
import hhs.server.domain.model.dto.request.comments.WriteCommentRequest;
import hhs.server.domain.model.dto.response.ResultResponse;
import hhs.server.domain.model.type.ResultCode;
import hhs.server.domain.persistence.Comments;
import hhs.server.domain.persistence.Projects;
import hhs.server.domain.persistence.User;
import hhs.server.domain.persistence.elasticsearch.ProjectDocuments;
import hhs.server.domain.repository.document.ProjectDocumentsRepository;
import hhs.server.domain.repository.jpa.CommentsRepository;
import hhs.server.domain.util.UpdateManager;
import hhs.server.domain.validator.Validator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class UserCommentsService implements CommentsService {

  private final CommentsRepository commentsRepository;
  private final ProjectDocumentsRepository projectDocumentsRepository;
  private final Validator validator;

  @BadgeCheck
  @Override
  public ResultResponse createComment(WriteCommentRequest request) {

    User commentUser = validator.validateAndGetUser(request.getUserId());

    Projects commentedProject = validator.validateAndGetProject(request.getProjectId());

    ProjectDocuments projectDocuments = projectDocumentsRepository.findById(
        commentedProject.getId()).orElseThrow(
        () -> new NotFoundException(ExceptionCode.PROJECT_DOCUMENTS_NOT_FOUND)
    );

    // 부모 댓글이 있는 경우 부모 댓글이 존재하는지 확인
    Long parentCommentId = request.getParentCommentId();

    Comments newComments;

    // 대댓글인 경우
    if (parentCommentId != null) {
      validator.isCommentExist(parentCommentId);

      Comments parentComment = validator.validateAndGetComment(parentCommentId);

      newComments = Comments.builder()
          .user(commentUser)
          .project(commentedProject)
          .contents(request.getContents())
          .parentComment(parentComment)
          .build();

      UpdateManager.incrementProjectCommentCount(projectDocuments, commentedProject);

      commentsRepository.save(newComments);

      projectDocumentsRepository.save(projectDocuments);

      return ResultResponse.of(ResultCode.COMMENT_REPLY_SUCCESS, newComments.getId());
    }
    // 댓글인 경우
    else {
      newComments = Comments.builder()
          .user(commentUser)
          .project(commentedProject)
          .contents(request.getContents())
          .build();

      UpdateManager.incrementProjectCommentCount(projectDocuments, commentedProject);

      commentsRepository.save(newComments);

      projectDocumentsRepository.save(projectDocuments);

      return ResultResponse.of(ResultCode.COMMENT_WRITE_SUCCESS, newComments.getId());
    }
  }

  @Override
  public ResultResponse updateComment(UpdateCommentRequest request) {

    validator.isUserExist(request.getUserId());

    validator.isProjectExist(request.getProjectId());

    Comments oldComment = validator.validateAndGetComment(request.getCommentId());

    oldComment.update(request);

    return ResultResponse.of(ResultCode.COMMENT_UPDATE_SUCCESS, oldComment.getId());
  }

  @Override
  public ResultResponse deleteComment(DeleteCommentRequest request) {

    validator.isUserExist(request.getUserId());

    Comments oldComments = validator.validateAndGetComment(request.getCommentId());

    if (!oldComments.getUser().getId().equals(request.getUserId())) {
      throw new UnmatchedUserException();
    }

    commentsRepository.delete(oldComments);

    return ResultResponse.of(ResultCode.COMMENT_DELETE_SUCCESS, oldComments.getId());
  }
}
