package hhs.server.domain.mapper;

import hhs.server.domain.model.dto.response.comments.Comment;
import hhs.server.domain.model.dto.response.comments.CommentsList;
import hhs.server.domain.model.dto.response.project.ListProjectResponse;
import hhs.server.domain.model.dto.response.project.ShortProjectDetail;
import hhs.server.domain.persistence.Comments;
import hhs.server.domain.persistence.Projects;
import java.util.List;

public class Mapper {

  public static CommentsList mapToCommentList(List<Comments> comments) {
    List<Comment> list = comments.stream().map(Mapper::mapToComment).toList();
    return new CommentsList((long) list.size(), list);
  }

  public static Comment mapToComment(Comments comments){
    return Comment.builder()
        .id(comments.getId())
        .parentId(comments.getParentComment() == null ? null : comments.getParentComment().getId())
        .contents(comments.getContents())
        .userNickname(comments.getUser().getNickname())
        .build();
  }

  public static ListProjectResponse mapToProjectResponse(List<ShortProjectDetail> projectDetails) {
    return new ListProjectResponse(projectDetails);
  }
  public static ShortProjectDetail mapToShortProjectDetail(Projects project) {
    return ShortProjectDetail.builder()
        .title(project.getTitle())
        .subject(project.getSubject())
        .build();
  }
}
