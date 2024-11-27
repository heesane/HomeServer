package hhs.server.domain.model.dto.request.projects;

import hhs.server.domain.model.type.Sorts;
import lombok.Getter;

@Getter
public class MyProjectListRequest extends ProjectListRequest {

  private final Long userId;

  public MyProjectListRequest(int page, int size, Sorts sort, Long userId) {
    super(page, size, sort);
    this.userId = userId;
  }
}
