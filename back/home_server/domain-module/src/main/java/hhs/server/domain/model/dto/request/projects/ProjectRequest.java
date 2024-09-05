package hhs.server.domain.model.dto.request.projects;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ProjectRequest {


  private final Long id;

  public static ProjectRequest of(Long id) {
    return new ProjectRequest(id);
  }
}
