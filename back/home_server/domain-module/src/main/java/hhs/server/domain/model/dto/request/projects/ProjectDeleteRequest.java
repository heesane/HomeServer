package hhs.server.domain.model.dto.request.projects;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProjectDeleteRequest {

  private final Long userId;

  private final Long projectId;

}
