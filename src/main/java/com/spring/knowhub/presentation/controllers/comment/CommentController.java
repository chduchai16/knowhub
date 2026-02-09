package com.spring.knowhub.presentation.controllers.comment;

import com.spring.knowhub.application.buses.CommandBus;
import com.spring.knowhub.application.buses.QueryBus;
import com.spring.knowhub.application.commands.comment.CreateCommentCommand;
import com.spring.knowhub.application.commands.comment.DeleteCommentCommand;
import com.spring.knowhub.application.queries.comment.GetCommentsByPostIdQuery;
import com.spring.knowhub.domain.models.comment.Comment;
import com.spring.knowhub.infrastructure.security.CustomUserDetails;
import com.spring.knowhub.presentation.mappers.comment.CommentResponseMapper;
import com.spring.knowhub.presentation.requests.comment.CreateCommentRequest;
import com.spring.knowhub.presentation.response.ApiResponse;
import com.spring.knowhub.presentation.response.PaginatedResponse;
import com.spring.knowhub.presentation.response.PaginationInfo;
import com.spring.knowhub.presentation.response.comment.CommentResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/comments")
@RestController
@RequiredArgsConstructor
@Slf4j
public class CommentController {

        private final CommandBus commandBus;
        private final QueryBus queryBus;
        private final CommentResponseMapper commentResponseMapper;

        @PostMapping
        public ResponseEntity<ApiResponse<?>> createComment(
                        @RequestBody CreateCommentRequest request,
                        @AuthenticationPrincipal CustomUserDetails userDetails) {
                log.info("POST /api/comments - userId={}, postId={}", userDetails.getUserId(), request.getPostId());

                Comment comment = commandBus.execute(new CreateCommentCommand(
                                request.getPostId(),
                                userDetails.getUserId(),
                                request.getParentId(),
                                request.getContent()));

                return ResponseEntity.status(201).body(
                                new ApiResponse<>(
                                                "SUCCESS",
                                                "Tạo bình luận thành công",
                                                commentResponseMapper.fromCommentToCommentResponse(comment)));
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<ApiResponse<?>> deleteComment(
                        @PathVariable Long id,
                        @AuthenticationPrincipal CustomUserDetails userDetails) {
                log.info("DELETE /api/comments/{} - userId={}", id, userDetails.getUserId());

                commandBus.execute(new DeleteCommentCommand(id, userDetails.getUserId()));

                return ResponseEntity.ok(
                                new ApiResponse<>(
                                                "SUCCESS",
                                                "Xóa bình luận thành công",
                                                null));
        }

        @GetMapping("/post/{postId}")
        public ResponseEntity<ApiResponse<?>> getCommentsByPostId(
                        @PathVariable Long postId,
                        @RequestParam(defaultValue = "0") int page,
                        @RequestParam(defaultValue = "10") int limit) {
                log.info("GET /api/comments/post/{} - page={}, limit={}", postId, page, limit);

                PageRequest pageable = PageRequest.of(page, limit, Sort.by(
                                Sort.Order.desc("createdAt")));

                Page<Comment> commentsPage = queryBus.execute(
                                new GetCommentsByPostIdQuery(postId, pageable));

                Page<CommentResponse> commentResponses = commentsPage
                                .map(commentResponseMapper::fromCommentToCommentResponse);

                PaginationInfo paginationInfo = new PaginationInfo(
                                commentResponses.getTotalElements(),
                                commentResponses.getTotalPages(),
                                commentResponses.getNumber(),
                                commentResponses.getSize());

                PaginatedResponse<CommentResponse> paginatedResponse = new PaginatedResponse<>(
                                commentResponses.getContent(),
                                paginationInfo);

                return ResponseEntity.ok(
                                new ApiResponse<>(
                                                "SUCCESS",
                                                "Lấy danh sách bình luận thành công cho post id = " + postId,
                                                paginatedResponse));
        }
}
