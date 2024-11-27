package hhs.server.domain.model.dto.request.likes;


import hhs.server.domain.model.type.LikeType;
import lombok.Getter;

@Getter
public class ProjectLikeRequest extends BaseLikeRequest {

  private final Long projectId;

  public ProjectLikeRequest(Long userId, LikeType likeType, Long projectId) {
    super(userId, likeType);
    this.projectId = projectId;
  }
}
