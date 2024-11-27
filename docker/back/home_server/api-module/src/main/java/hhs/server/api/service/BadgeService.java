package hhs.server.api.service;


import hhs.server.domain.model.dto.request.badge.CreateBadgeRequest;
import hhs.server.domain.model.dto.request.badge.DeleteBadgeRequest;
import hhs.server.domain.model.dto.request.badge.UpdateBadgeRequest;
import hhs.server.domain.model.dto.response.ResultResponse;

public interface BadgeService {

  ResultResponse getAllBadges();

  ResultResponse createNewBadge(CreateBadgeRequest request);

  ResultResponse updateBadge(UpdateBadgeRequest request);

  ResultResponse deleteBadge(DeleteBadgeRequest request);
}
