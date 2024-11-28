package hhs.server.domain.repository.document;

import hhs.server.domain.persistence.elasticsearch.ProjectDocuments;
import java.util.List;
import java.util.Optional;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProjectDocumentsRepository extends
    ElasticsearchRepository<ProjectDocuments, Long> {

  Optional<ProjectDocuments> findByTitle(String title);

  @Query("{\"wildcard\": {\"title\": \"*?0*\"}}")
  List<ProjectDocuments> findByTitleLike(String title);

  Optional<ProjectDocuments> findById(Long id);
}
