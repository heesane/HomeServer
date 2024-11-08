package hhs.server.domain.repository.jpa;

import hhs.server.domain.persistence.ProjectLikes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectsLikeRepository extends JpaRepository<ProjectLikes, Long> {

  boolean existsByProjectIdAndUserId(Long projectId, Long userId);

  void deleteByProjectIdAndUserId(Long projectId, Long userId);
}
