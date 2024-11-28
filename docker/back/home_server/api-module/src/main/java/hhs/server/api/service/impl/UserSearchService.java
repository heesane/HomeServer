package hhs.server.api.service.impl;

import hhs.server.api.service.SearchService;
import hhs.server.domain.exception.ExceptionCode;
import hhs.server.common.exceptions.exception.NotFoundException;
import hhs.server.domain.model.dto.response.project.ProjectDetail;
import hhs.server.domain.persistence.Projects;
import hhs.server.domain.persistence.elasticsearch.ProjectDocuments;
import hhs.server.domain.repository.document.ProjectDocumentsRepository;
import hhs.server.domain.repository.jpa.ProjectRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserSearchService implements SearchService {

  private final ProjectDocumentsRepository projectDocumentsRepository;
  private final ProjectRepository projectRepository;

  @Override
  public ProjectDocuments searchProjectByTitle(String keyword) {
    return projectDocumentsRepository.findByTitle(keyword).orElseThrow(
        () -> new NotFoundException(ExceptionCode.PROJECT_NOT_FOUND)
    );
  }

  @Override
  public List<ProjectDocuments> searchProjectByTitleLike(String keyword) {
    return projectDocumentsRepository.findByTitleLike(keyword);
  }

  @Override
  public ProjectDetail searchProjectByTitleInRDB(String keyword) {
    return projectRepository.findByTitle(keyword).map(ProjectDetail::new).orElseThrow(
        () -> new NotFoundException(ExceptionCode.PROJECT_NOT_FOUND)
    );
  }

  @Override
  public List<ProjectDetail> searchProjectByTitleInRDBLike(String keyword) {
    List<Projects> projects = projectRepository.findAllByTitleLike("%"+keyword+"%");
    return projects.stream().map(ProjectDetail::new).toList();
  }

  @Override
  public Iterable<ProjectDocuments> findAllInElasticSearch() {
    return projectDocumentsRepository.findAll();
  }
}
