package hhs.server.domain.model.dto.response.comments;


import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommentsList {

  private final Long count;

  private final List<Comment> comments;
}
