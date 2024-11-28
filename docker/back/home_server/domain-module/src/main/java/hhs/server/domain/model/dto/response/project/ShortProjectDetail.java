package hhs.server.domain.model.dto.response.project;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class ShortProjectDetail {

  private final String title;
  private final String subject;

}
