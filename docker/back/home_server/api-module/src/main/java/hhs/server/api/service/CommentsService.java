package hhs.server.api.service;


import hhs.server.domain.model.dto.request.comments.DeleteCommentRequest;
import hhs.server.domain.model.dto.request.comments.UpdateCommentRequest;
import hhs.server.domain.model.dto.request.comments.WriteCommentRequest;
import hhs.server.domain.model.dto.response.ResultResponse;

public interface CommentsService {

  // 댓글 작성
  ResultResponse createComment(WriteCommentRequest request);

  // 댓글 수정
  ResultResponse updateComment(UpdateCommentRequest request);

  // 댓글 삭제
  ResultResponse deleteComment(DeleteCommentRequest request);
}
