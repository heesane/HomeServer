package hhs.server.api.service.impl;


import hhs.server.api.service.ProjectService;
import hhs.server.common.exceptions.ExceptionCode;
import hhs.server.common.exceptions.exception.NotFoundException;
import hhs.server.common.exceptions.exception.UnmatchedUserException;
import hhs.server.domain.model.dto.response.project.ListProjectResponse;
import hhs.server.domain.model.dto.response.project.ProjectDetailResponse;
import hhs.server.domain.aop.badge.BadgeCheck;
import hhs.server.domain.mapper.Mapper;
import hhs.server.domain.model.dto.response.project.ShortProjectDetail;
import hhs.server.domain.model.dto.request.projects.MyProjectListRequest;
import hhs.server.domain.model.dto.request.projects.ProjectCreateRequest;
import hhs.server.domain.model.dto.request.projects.ProjectDeleteRequest;
import hhs.server.domain.model.dto.request.projects.ProjectListRequest;
import hhs.server.domain.model.dto.request.projects.ProjectRequest;
import hhs.server.domain.model.dto.request.projects.ProjectUpdateRequest;
import hhs.server.domain.model.dto.response.ResultResponse;
import hhs.server.domain.model.dto.response.project.ProjectDetail;
import hhs.server.domain.model.type.PictureType;
import hhs.server.domain.model.type.ResultCode;
import hhs.server.domain.model.type.Sorts;
import hhs.server.domain.persistence.Projects;
import hhs.server.domain.persistence.User;
import hhs.server.domain.persistence.elasticsearch.ProjectDocuments;
import hhs.server.domain.repository.document.ProjectDocumentsRepository;
import hhs.server.domain.repository.jpa.ProjectRepository;
import hhs.server.domain.util.PictureManager;
import hhs.server.domain.validator.Validator;
import jakarta.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Service
@RequiredArgsConstructor
@Validated
public class UserProjectService implements ProjectService {

  private final ProjectRepository projectRepository;
  private final ProjectDocumentsRepository projectDocumentsRepository;
  private final PictureManager pictureManager;
  private final Validator validator;

  @Override
  public ListProjectResponse listProjects(ProjectListRequest request) {

    Pageable pageable = PageRequest.of(request.getPage(), request.getSize());

    Sorts sort = request.getSort();

    // 추후 개발을 위해 우선 선언
    Page<Projects> sortedProjects;

    if (sort == Sorts.LATEST) {
      pageable.getSort().and(Sort.by(Sort.Direction.DESC, "registeredAt"));
      sortedProjects = projectRepository.findAll(pageable);

      List<ShortProjectDetail> collect = sortedProjects.stream()
          .map(Mapper::mapToShortProjectDetail)
          .collect(Collectors.toList());

      return new ListProjectResponse(collect);

    } else if (sort == Sorts.LIKE) {
//      return projectRepository.findAllByLike(request); (추후 좋아요 기능 구현시)
      return null;
    } else if (sort == Sorts.COMMENTS) {
//      return projectRepository.findAllByComments(request); (추후 댓글 기능 구현시)
      return null;
    }
    return null;
  }

  @Override
  public ProjectDetailResponse getProjectDetail(ProjectRequest request) {

    Projects project = validator.validateAndGetProject(request.getId());
    return new ProjectDetailResponse(new ProjectDetail(project));
  }

  @Override
  public ListProjectResponse getMyProjectDetail(MyProjectListRequest request) {

    validator.isUserExist(request.getUserId());

    Pageable pageable;

    Sorts sort = request.getSort();

    // 추후 개발을 위해 우선 선언
    Page<Projects> sortedProjects;

    if (sort == Sorts.LATEST) {
      pageable = PageRequest.of(request.getPage(), request.getSize(),
          Sort.by(Sort.Direction.DESC, "registeredAt"));
      sortedProjects = projectRepository.findAllByUserId(
          request.getUserId(),
          pageable);

      List<ShortProjectDetail> collect = sortedProjects.stream()
          .map(Mapper::mapToShortProjectDetail)
          .collect(Collectors.toList());

      return new ListProjectResponse(collect);

    } else if (sort == Sorts.LIKE) {
//      return projectRepository.findAllByLike(request); (추후 좋아요 기능 구현시)
      return null;
    } else if (sort == Sorts.COMMENTS) {
//      return projectRepository.findAllByComments(request); (추후 댓글 기능 구현시)
      return null;
    }
    return null;
  }

  @BadgeCheck
  @Override
  @Transactional
  public ResultResponse createProject(ProjectCreateRequest request)
      throws IOException {

    Long userId = request.getUserId();

    User user = validator.validateAndGetUser(userId);

    String uploadedSystemArchitectureUrl = pictureManager.upload(
        userId,
        request.getTitle(),
        request.getSystemArchitecturePicture(),
        PictureType.SYSTEM_ARCHITECTURE
    );

    String uploadedErdUrl = pictureManager.upload(
        userId,
        request.getTitle(),
        request.getErdPicture(),
        PictureType.ERD
    );

    Projects project = Projects.builder()
        .title(request.getTitle())
        .subject(request.getSubject())
        .feature(request.getFeature())
        .contents(request.getContents())
        .skills(request.getSkills())
        .tools(request.getTools())
        .systemArchitectureUrl(uploadedSystemArchitectureUrl)
        .erdUrl(uploadedErdUrl)
        .githubUrl(request.getGithubUrl())
        .user(user)
        .build();

    projectRepository.save(project);

    // Elasticsearch에 저장
    createProjectElasticsearch(project);

    return ResultResponse.of(ResultCode.PROJECT_CREATE_SUCCESS);
  }

  @Override
  @Transactional
  public ResultResponse updateProject(ProjectUpdateRequest request)
      throws IOException {

    Long userId = request.getUserId();

    validator.isUserExist(userId);

    Long projectId = request.getProjectId();

    Projects project = validator.validateAndGetProject(projectId);

    // 사용자가 등록한 프로젝트가 아닌 경우
    if (!project.getUser().getId().equals(userId)) {
      throw new UnmatchedUserException();
    }

    // 업데이트 요청 시 이미지가 있는 경우만 변경
    if (request.getSystemArchitecturePicture() != null && !request.getSystemArchitecturePicture()
        .isEmpty()) {
      String uploadedSystemArchitectureUrl = pictureManager.upload(
          userId,
          request.getTitle(),
          request.getSystemArchitecturePicture(),
          PictureType.SYSTEM_ARCHITECTURE
      );
      project.updateSystemArchitecture(uploadedSystemArchitectureUrl);
    }

    if (request.getErdPicture() != null) {
      String uploadedErdUrl = pictureManager.upload(
          userId,
          request.getTitle(),
          request.getErdPicture(),
          PictureType.ERD
      );
      project.updateErd(uploadedErdUrl);
    }

    project.update(request);

    updateProjectsElasticsearch(project);

    return ResultResponse.of(ResultCode.PROJECT_UPDATE_SUCCESS);
  }

  @Override
  @Transactional
  public ResultResponse deleteProject(ProjectDeleteRequest request) {

    Long userId = request.getUserId();

    validator.isUserExist(userId);

    Long projectId = request.getProjectId();

    Projects project = projectRepository.findById(projectId).orElseThrow(
        () -> new NotFoundException(ExceptionCode.PROJECT_NOT_FOUND)
    );

    // 사용자가 등록한 프로젝트가 아닌 경우
    if (!project.getUser().getId().equals(userId)) {
      throw new UnmatchedUserException();
    }

    // 삭제된 프로젝트인 경우 처리 고려 중
    // 1. 댓글 및 좋아요 기능 CascadeType.ALL로 처리하여 자동 삭제
    // 2. 삭제된 프로젝트 하위 댓글들 모두 soft delete

    projectRepository.delete(project);

    projectDocumentsRepository.deleteById(projectId);

    return ResultResponse.of(ResultCode.PROJECT_DELETE_SUCCESS);
  }

  @Transactional
  public void createProjectElasticsearch(Projects project) {
    ProjectDocuments newProjectDocuments = ProjectDocuments.builder().
        id(project.getId()).
        title(project.getTitle()).
        subject(project.getSubject()).
        feature(project.getFeature()).
        contents(project.getContents()).
        skills(project.getSkills().stream().map(Enum::name).collect(Collectors.toList())).
        tools(project.getTools().stream().map(Enum::name).collect(Collectors.toList())).
        systemArchitecture(project.getSystemArchitectureUrl()).
        erd(project.getErdUrl()).
        githubLink(project.getGithubUrl()).
        authorName(project.getUser().getNickname()).
        comments(
            project.getComments() != null ? project.getComments().stream()
                .map(Mapper::mapToComment).toList()
                : null).
        commentsCount(project.getComments() != null ? (long) project.getComments().size() : 0L).
        likeCount(project.getLikeCounts()).
        build();
    projectDocumentsRepository.save(newProjectDocuments);
  }

  @Transactional
  public void updateProjectsElasticsearch(Projects projects) {
    ProjectDocuments projectDocuments = projectDocumentsRepository.findById(projects.getId())
        .orElseThrow(
            () -> new NotFoundException(ExceptionCode.PROJECT_NOT_FOUND)
        );
    projectDocuments.update(projects);
    projectDocumentsRepository.save(projectDocuments);
  }
}

