package hhs.server.api.service;


import hhs.server.domain.model.dto.response.project.ProjectDetail;
import hhs.server.domain.persistence.elasticsearch.ProjectDocuments;
import java.util.List;

public interface SearchService {

  List<ProjectDocuments> searchProjectByTitleLike(String keyword);

  ProjectDocuments searchProjectByTitle(String keyword);

  List<ProjectDetail> searchProjectByTitleInRDBLike(String keyword);

  ProjectDetail searchProjectByTitleInRDB(String keyword);

  Object findAllInElasticSearch();
}
