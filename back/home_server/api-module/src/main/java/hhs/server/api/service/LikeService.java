package hhs.server.api.service;


import hhs.server.domain.model.dto.request.likes.BaseLikeRequest;
import hhs.server.domain.model.dto.response.ResultResponse;

public interface LikeService {

  ResultResponse like(BaseLikeRequest projectLikeRequest);
}
