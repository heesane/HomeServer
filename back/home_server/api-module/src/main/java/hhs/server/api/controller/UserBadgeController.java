package hhs.server.api.controller;


import hhs.server.api.service.impl.UserBadgeService;
import hhs.server.domain.model.dto.request.badge.CreateBadgeRequest;
import hhs.server.domain.model.dto.request.badge.DeleteBadgeRequest;
import hhs.server.domain.model.dto.request.badge.UpdateBadgeRequest;
import hhs.server.domain.model.dto.response.ResultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/badge")
public class UserBadgeController {

  private final UserBadgeService userBadgeService;

  @GetMapping("/all")
  public ResponseEntity<ResultResponse> getAllBadges() {
    return ResponseEntity.ok(userBadgeService.getAllBadges());
  }

  @PostMapping("/create")
  public ResponseEntity<ResultResponse> createNewBadge(@RequestBody CreateBadgeRequest request) {
    return ResponseEntity.ok(userBadgeService.createNewBadge(request));
  }

  @PatchMapping("/update")
  public ResponseEntity<ResultResponse> updateBadge(@RequestBody UpdateBadgeRequest request) {
    return ResponseEntity.ok(userBadgeService.updateBadge(request));
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<ResultResponse> deleteBadge(@PathVariable("id") DeleteBadgeRequest request) {
    return ResponseEntity.ok(userBadgeService.deleteBadge(request));
  }

}
