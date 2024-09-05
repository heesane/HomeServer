package hhs.server.domain.model.dto.request.comments;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BaseCommentsRequest {

  private Long userId;
  private Long projectId;
  private String contents;
}
