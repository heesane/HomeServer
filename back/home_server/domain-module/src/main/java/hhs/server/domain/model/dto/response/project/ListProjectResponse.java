package hhs.server.domain.model.dto.response.project;


import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ListProjectResponse {

  private final List<ShortProjectDetail> projectDetails;
}
