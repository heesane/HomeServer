package hhs.server.domain.model.dto.response.comments;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Comment {

  public Long id;

  public Long parentId;

  public String contents;

  public String userNickname;
}
