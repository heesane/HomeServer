package hhs.server.domain.model.dto.request.projects;


import hhs.server.domain.model.type.Skills;
import hhs.server.domain.model.type.Tools;

import java.util.List;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
public class ProjectUpdateRequest extends BaseProjectRequest {

  private final Long userId;
  private final Long projectId;

  public ProjectUpdateRequest(
      Long userId,
      Long projectId,
      String title,
      String subject,
      String feature,
      String contents,
      List<Skills> skills,
      List<Tools> tools,
      MultipartFile systemArchitecturePicture,
      MultipartFile erdPicture,
      String githubUrl,
      boolean visible
  ) {
    super(title, subject, feature, contents, skills, tools, systemArchitecturePicture, erdPicture,
        githubUrl, visible);
    this.userId = userId;
    this.projectId = projectId;
  }
}
