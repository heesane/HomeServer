package hhs.server.domain.model.dto.request.likes;

import hhs.server.domain.model.type.LikeType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class BaseLikeRequest {

  private final Long userId;

  private final LikeType likeType;
}
