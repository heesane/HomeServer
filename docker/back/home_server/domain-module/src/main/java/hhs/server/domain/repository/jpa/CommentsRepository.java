package hhs.server.domain.repository.jpa;


import hhs.server.domain.persistence.Comments;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentsRepository extends JpaRepository<Comments, Long> {

  @Query("select c.id from Comments c")
  List<Long> findAllIdWithDetail();

}
