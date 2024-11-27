package hhs.server.domain.model.dto.request.comments;


import hhs.server.domain.aop.badge.BadgeInterface;
import lombok.Getter;
import lombok.ToString;

@ToString(callSuper = true)
@Getter
public class WriteCommentRequest extends BaseCommentsRequest implements BadgeInterface {

  private Long parentCommentId = null;

  public WriteCommentRequest(Long userId, Long projectId, String contents, Long parentCommentId) {
    super(userId, projectId, contents);
    // 부모 댓글이 없는 경우 null로 설정
    this.parentCommentId = parentCommentId;
  }

  @Override
  public Long getId() {
    return super.getUserId();
  }
}