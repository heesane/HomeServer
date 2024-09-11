package hhs.server.domain.model.dto.request.projects;

import hhs.server.domain.model.type.Skills;
import hhs.server.domain.model.type.Tools;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@AllArgsConstructor
public class BaseProjectRequest {


  private final String title;

  private final String subject;


  private final String feature;

  private final String contents;


  private final List<Skills> skills;


  private final List<Tools> tools;

  private final MultipartFile systemArchitecturePicture;

  private final MultipartFile erdPicture;

  private final String githubUrl;

  private final boolean visible;

}
