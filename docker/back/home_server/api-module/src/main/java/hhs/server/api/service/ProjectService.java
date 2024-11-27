package hhs.server.api.service;

import hhs.server.domain.model.dto.response.project.ProjectDetailResponse;
import hhs.server.domain.model.dto.request.projects.MyProjectListRequest;
import hhs.server.domain.model.dto.request.projects.ProjectCreateRequest;
import hhs.server.domain.model.dto.request.projects.ProjectDeleteRequest;
import hhs.server.domain.model.dto.request.projects.ProjectListRequest;
import hhs.server.domain.model.dto.request.projects.ProjectRequest;
import hhs.server.domain.model.dto.request.projects.ProjectUpdateRequest;
import hhs.server.domain.model.dto.response.ResultResponse;
import hhs.server.domain.model.dto.response.project.ListProjectResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public interface ProjectService {

  // 전체 조회(각 프로젝트별 title, subject)
  ListProjectResponse listProjects(ProjectListRequest request);

  // 단건 조회 (프로젝트별 상세정보)
  ProjectDetailResponse getProjectDetail(ProjectRequest request);

  // 내 프로젝트 전체 조회
  ListProjectResponse getMyProjectDetail(MyProjectListRequest request);

  // 등록
  ResultResponse createProject(ProjectCreateRequest request)
      throws IOException, NoSuchAlgorithmException;

  // 수정
  ResultResponse updateProject(ProjectUpdateRequest request)
      throws IOException, NoSuchAlgorithmException;

  // 삭제
  ResultResponse deleteProject(ProjectDeleteRequest request);
}
