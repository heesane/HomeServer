package hhs.server.api.service.impl;

import hhs.server.api.service.BadgeService;
import hhs.server.common.exceptions.ExceptionCode;
import hhs.server.common.exceptions.exception.DuplicateBadgeException;
import hhs.server.common.exceptions.exception.NotFoundException;
import hhs.server.domain.model.dto.request.badge.CreateBadgeRequest;
import hhs.server.domain.model.dto.request.badge.DeleteBadgeRequest;
import hhs.server.domain.model.dto.request.badge.UpdateBadgeRequest;
import hhs.server.domain.model.dto.response.ResultResponse;
import hhs.server.domain.model.type.ResultCode;
import hhs.server.domain.persistence.Badge;
import hhs.server.domain.repository.jpa.BadgeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserBadgeService implements BadgeService {

  private final BadgeRepository badgeRepository;

  @Override
  public ResultResponse getAllBadges() {
    return ResultResponse.of(ResultCode.BADGE_LIST_SUCCESS, badgeRepository.findAll());
  }

  @Override
  @Transactional
  public ResultResponse createNewBadge(CreateBadgeRequest request) {

    if(badgeRepository.existsByName(request.getName())){
      throw new DuplicateBadgeException(ExceptionCode.BADGE_ALREADY_EXISTS);
    }

    Badge badge = Badge.builder()
        .name(request.getName())
        .description(request.getDescription())
        .requiredProjectCount(request.getRequiredProjects())
        .requiredCommentCount(request.getRequiredComments())
        .build();

    badgeRepository.save(badge);

    return ResultResponse.of(ResultCode.BADGE_CREATE_SUCCESS);
  }

  @Override
  @Transactional
  public ResultResponse updateBadge(UpdateBadgeRequest request) {
    Long oldBadgeId = request.getId();

    Badge badge = badgeRepository.findById(oldBadgeId).orElseThrow(
        () -> new NotFoundException(ExceptionCode.BADGE_NOT_FOUND)
    );

    badge.update(request);

    return ResultResponse.of(ResultCode.BADGE_UPDATE_SUCCESS);
  }

  @Override
  @Transactional
  public ResultResponse deleteBadge(DeleteBadgeRequest request) {

    Long badgeId = request.getId();

    if(!badgeRepository.existsById(badgeId)){
      throw new NotFoundException(ExceptionCode.BADGE_NOT_FOUND);
    }

    badgeRepository.deleteById(badgeId);

    return ResultResponse.of(ResultCode.BADGE_DELETE_SUCCESS);
  }
}
