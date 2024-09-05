package hhs.server.domain.repository.jpa;

import hhs.server.domain.persistence.CommentLikes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentsLikeRepository extends JpaRepository<CommentLikes, Long> {

  boolean existsByCommentIdAndUserId(Long commentId, Long userId);

  void deleteByCommentIdAndUserId(Long commentId, Long userId);

  Long countByUserId(Long userId);
}
