package hhs.server.domain.model.dto.request.badge;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class DeleteBadgeRequest {
  private final Long id;
}
