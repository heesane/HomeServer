package hhs.server.api.controller;


import hhs.server.api.service.impl.UserProjectService;
import hhs.server.domain.model.dto.request.projects.MyProjectListRequest;
import hhs.server.domain.model.dto.request.projects.ProjectCreateRequest;
import hhs.server.domain.model.dto.request.projects.ProjectDeleteRequest;
import hhs.server.domain.model.dto.request.projects.ProjectListRequest;
import hhs.server.domain.model.dto.request.projects.ProjectRequest;
import hhs.server.domain.model.dto.request.projects.ProjectUpdateRequest;
import hhs.server.domain.model.dto.response.ResultResponse;
import hhs.server.domain.model.type.ResultCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Tag(name = "UserProjectController", description = "프로젝트 API")
@RequiredArgsConstructor
@RequestMapping("/api/v1/project")
@RestController
public class UserProjectController {

  private final UserProjectService userProjectService;

  @GetMapping(value = "/list")
  @Operation(
      summary = "Get Projects",
      description = "프로젝트 리스트 조회"
  )
  public ResponseEntity<ResultResponse> getProjects(
      @ModelAttribute ProjectListRequest projectListRequest) {
    return ResponseEntity.ok(
        ResultResponse.of(ResultCode.PROJECT_LIST_SUCCESS,
            userProjectService.listProjects(projectListRequest)));
  }

  @GetMapping(value = "/{projectId}")
  @Operation(
      summary = "Get Project Detail",
      description = "프로젝트 상세 조회"
  )
  public ResponseEntity<ResultResponse> getProjectDetail(
      @PathVariable("projectId") Long projectId) {
    return ResponseEntity.ok(ResultResponse.of(ResultCode.PROJECT_DETAIL_SUCCESS,
        userProjectService.getProjectDetail(ProjectRequest.of(projectId))));
  }

  @GetMapping(value = "/myproject/list")
  @Operation(
      summary = "Get My Projects",
      description = "내 프로젝트 리스트 조회"
  )
  public ResponseEntity<ResultResponse> getMyProjects(
      @ModelAttribute @Valid MyProjectListRequest request) {
    return ResponseEntity.ok(ResultResponse.of(ResultCode.PROJECT_LIST_SUCCESS,
        userProjectService.getMyProjectDetail(request)));
  }

  @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  @Operation(
      summary = "Create Project",
      description = "프로젝트 생성"
  )
  public ResponseEntity<ResultResponse> createProject(
      @ModelAttribute @Valid ProjectCreateRequest request)
      throws IOException {
    return ResponseEntity.ok(ResultResponse.of(ResultCode.PROJECT_CREATE_SUCCESS,
        userProjectService.createProject(request)));
  }

  @PatchMapping(value = "/update", consumes = {"multipart/form-data"})
  @Operation(
      summary = "Update Project",
      description = "프로젝트 수정"
  )
  public ResponseEntity<ResultResponse> updateProject(
      @ModelAttribute @Valid ProjectUpdateRequest request)
      throws IOException {
    return ResponseEntity.ok(ResultResponse.of(ResultCode.PROJECT_UPDATE_SUCCESS,
        userProjectService.updateProject(request)));
  }

  @DeleteMapping(value = "/delete", consumes = {"application/json"})
  @Operation(
      summary = "Delete Project",
      description = "프로젝트 삭제"
  )
  public ResponseEntity<ResultResponse> deleteProject(
      @RequestBody @Valid ProjectDeleteRequest request) {
    return ResponseEntity.ok(ResultResponse.of(ResultCode.PROJECT_DELETE_SUCCESS,
        userProjectService.deleteProject(request)));
  }
}


