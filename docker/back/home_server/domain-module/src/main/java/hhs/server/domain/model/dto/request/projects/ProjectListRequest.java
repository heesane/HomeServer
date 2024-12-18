package hhs.server.domain.model.dto.request.projects;

import hhs.server.domain.model.type.Sorts;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ProjectListRequest {

  private final int page;
  private final int size;
  private final Sorts sort;

  public ProjectListRequest(Integer page, Integer size, Sorts sort) {
    this.page = page;
    this.size = (size != 0) ? size : 10;
    this.sort = (sort != null) ? sort : Sorts.LATEST;
  }
}
