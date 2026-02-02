package com.spring.knowhub.presentation.controllers.post;

import com.spring.knowhub.application.buses.CommandBus;
import com.spring.knowhub.application.buses.QueryBus;
import com.spring.knowhub.application.commands.post.post.CreatePostCommand;
import com.spring.knowhub.application.commands.post.post.DeletePostCommand;
import com.spring.knowhub.application.commands.post.post.UpdatePostCommand;
import com.spring.knowhub.application.queries.post.post.GetNewFeedsQuery;
import com.spring.knowhub.application.queries.post.post.GetPagedPostQuery;
import com.spring.knowhub.application.queries.post.post.GetPostByIdQuery;
import com.spring.knowhub.domain.models.post.Post;
import com.spring.knowhub.infrastructure.security.CustomUserDetails;
import com.spring.knowhub.presentation.mappers.post.PostResponseMapper;
import com.spring.knowhub.presentation.requests.post.post.CreatePostRequest;
import com.spring.knowhub.presentation.requests.post.post.UpdatePostRequest;
import com.spring.knowhub.presentation.response.ApiResponse;
import com.spring.knowhub.presentation.response.PaginatedResponse;
import com.spring.knowhub.presentation.response.PaginationInfo;
import com.spring.knowhub.presentation.response.post.PostResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/posts")
@RestController
@RequiredArgsConstructor
@Slf4j
public class PostController {

        private final CommandBus commandBus;
        private final QueryBus queryBus;
        private final PostResponseMapper postResponseMapper;

        @GetMapping
        public ResponseEntity<ApiResponse<?>> getPagedPosts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50") int limit,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String username,
            @RequestParam(defaultValue =  "published") String status
        ) {
            log.info("GET /api/posts - page={}, limit={}", page, limit);
            PageRequest pageable = PageRequest.of(page, limit, Sort.by(
                Sort.Order.desc("createdAt")
            ));
            Page<Post> postsPage = queryBus.execute(
                new GetPagedPostQuery(keyword, status, username , pageable)
            );
            Page<PostResponse> postResponses = postsPage.map(postResponseMapper::fromPostToPostResponse);


            PaginationInfo paginationInfo = new PaginationInfo(
                postResponses.getTotalElements(),
                postResponses.getTotalPages(),
                postResponses.getNumber(),
                postResponses.getSize()
            );

            PaginatedResponse <PostResponse> paginatedResponse = new PaginatedResponse<>(
                postResponses.getContent(),
                paginationInfo
            );

            return ResponseEntity.ok(
                new ApiResponse<>(
                    "SUCCESS",
                    "Lấy danh sách bài viết phân trang thành công",
                    paginatedResponse
                )
            );
        }

        @GetMapping("/feeds")
        public ResponseEntity<ApiResponse<?>> getFeedPosts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50") int limit,
            @AuthenticationPrincipal CustomUserDetails userDetails
        ) {
            log.info("GET /api/posts/feeds - page={}, limit={}", page, limit);
            PageRequest pageable = PageRequest.of(page, limit, Sort.by(
                    Sort.Order.desc("createdAt")
            ));
            Page<Post> postsPage = queryBus.execute(
                    new GetNewFeedsQuery(pageable)
            );
            Page<PostResponse> postResponses = postsPage.map(postResponseMapper::fromPostToPostResponse);

            PaginationInfo paginationInfo = new PaginationInfo(
                    postResponses.getTotalElements(),
                    postResponses.getTotalPages(),
                    postResponses.getNumber(),
                    postResponses.getSize()
            );

            PaginatedResponse <PostResponse> paginatedResponse = new PaginatedResponse<>(
                    postResponses.getContent(),
                    paginationInfo
            );

            return ResponseEntity.ok(
                    new ApiResponse<>(
                            "SUCCESS",
                            "Lấy danh sách bài viết phân trang thành công",
                            paginatedResponse
                    )
            );
        }

        @GetMapping("/{id}")
        public ResponseEntity<ApiResponse<?>> getPostById(
            @PathVariable Long id
        ) {
            log.info("GET /api/posts/{}", id);
            Post post = queryBus.execute(new GetPostByIdQuery(id));
            return ResponseEntity.ok(
                new ApiResponse<>(
                    "SUCCESS",
                    "Lấy bài viết thành công",
                    postResponseMapper.fromPostToPostResponse(post))
            );
        }

        @PostMapping
        public ResponseEntity<ApiResponse<?>> createPost(
            @RequestBody CreatePostRequest request,
            @AuthenticationPrincipal CustomUserDetails userDetails
        ) {
            log.info("POST /api/posts - userId={}, content={}", userDetails.getUserId(), request.getContent());
            Long postId = commandBus.execute(
                new CreatePostCommand(
                    userDetails.getUserId(),
                    request.getContent(),
                    request.getPrivacy(),
                    request.getMediaIds(),
                    request.getTagIds(),
                    request.getStatus()
                )
            );

            return ResponseEntity.status(201).body(
                new ApiResponse<>(
                    "SUCCESS",
                    "Tạo bài viết thành công",
                    postId
                )
            );
        }

        @PutMapping
        public ResponseEntity<ApiResponse<?>> updatePost(
            @RequestBody UpdatePostRequest request,
            @AuthenticationPrincipal CustomUserDetails userDetails
        ) {
            log.info("PUT /api/posts - id={}, userId={}", request.getId(), userDetails.getUsername());
            Long userId = userDetails.getUserId();
            Long postId = commandBus.execute(
                new UpdatePostCommand(
                    request.getId(),
                    userId,
                    request.getContent(),
                    request.getPrivacy(),
                    request.getMediaIds(),
                    request.getTagIds(),
                    request.getStatus()
                )
            );
            return ResponseEntity.ok(
                new ApiResponse<>(
                    "SUCCESS",
                    "Cập nhật bài viết thành công",
                    postId)
            );
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<ApiResponse<?>> deletePost(
            @PathVariable Long id
        ) {
            log.info("DELETE /api/posts/{}", id);
            commandBus.execute(new DeletePostCommand(id));
            return ResponseEntity.ok(
                new ApiResponse<>(
                "SUCCESS",
                "Xóa bài viết thành công",
                null)
            );
        }
}
