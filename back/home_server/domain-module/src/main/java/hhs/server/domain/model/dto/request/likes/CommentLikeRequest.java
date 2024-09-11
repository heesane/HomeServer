package hhs.server.domain.model.dto.request.likes;


import hhs.server.domain.model.type.LikeType;
import lombok.Getter;

@Getter
public class CommentLikeRequest extends BaseLikeRequest {

  private final Long commentId;

  public CommentLikeRequest(Long userId, LikeType likeType, Long commentId) {
    super(userId, likeType);
    this.commentId = commentId;
  }
}
