package hhs.server.domain.model.dto.response.project;


import hhs.server.domain.mapper.Mapper;
import hhs.server.domain.model.dto.response.comments.CommentsList;
import hhs.server.domain.model.type.Skills;
import hhs.server.domain.model.type.Tools;
import hhs.server.domain.persistence.Projects;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;

@Getter
public class ProjectDetail {

  private final String title;
  private final String subject;
  private final String feature;
  private final String contents;
  private final List<Skills> skills;
  private final List<Tools> tools;
  private final String systemArchitectureUrl;
  private final String erdUrl;
  private final String githubUrl;
  private final Long userId;
  private final LocalDateTime registeredAt;
  private final CommentsList comments;
  private final Long likes;
  private final Long commentCounts;

  public ProjectDetail(Projects project) {
    this.title = project.getTitle();
    this.subject = project.getSubject();
    this.feature = project.getFeature();
    this.contents = project.getContents();
    this.skills = project.getSkills();
    this.tools = project.getTools();
    this.systemArchitectureUrl = project.getSystemArchitectureUrl();
    this.erdUrl = project.getErdUrl();
    this.githubUrl = project.getGithubUrl();
    this.userId = project.getUser().getId();
    this.registeredAt = project.getRegisteredAt();
    this.comments = Mapper.mapToCommentList(project.getComments());
    this.likes = project.getLikeCounts();
    this.commentCounts = (long)project.getComments().size();

  }
}
