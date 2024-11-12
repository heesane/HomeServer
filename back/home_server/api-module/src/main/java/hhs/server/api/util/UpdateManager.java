package hhs.server.api.util;


import hhs.server.domain.aop.lock.DistributedLock;
import hhs.server.domain.persistence.Comments;
import hhs.server.domain.persistence.Projects;
import hhs.server.domain.persistence.elasticsearch.ProjectDocuments;

public class UpdateManager {

  @DistributedLock
  public static void incrementProjectCommentCount(ProjectDocuments projectDocuments, Projects projects) {
    projectDocuments.update(projects);
  }

  @DistributedLock
  public static void updateProjectLikeCount(Projects project, Long likeCount) {
    project.updateLikeCounts(likeCount);
  }

  @DistributedLock
  public static void updateCommentLikeCount(Comments comments, Long likeCount) {
    comments.updateLikes(likeCount);
  }
}
