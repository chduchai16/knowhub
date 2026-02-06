package com.spring.knowhub.presentation.controllers.post;

import com.spring.knowhub.application.buses.CommandBus;
import com.spring.knowhub.application.commands.post.postlike.CreatePostLikeCommand;
import com.spring.knowhub.application.commands.post.postlike.DeletePostLikeCommand;
import com.spring.knowhub.infrastructure.security.CustomUserDetails;
import com.spring.knowhub.presentation.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
@Slf4j
public class PostLikeController {

    private final CommandBus commandBus;

    @PostMapping("/{postId}/likes")
    public ResponseEntity<ApiResponse<?>> likePost(
            @PathVariable Long postId,
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        log.info("POST /api/posts/{}/likes - userId={}", postId, userDetails.getUserId());
        CreatePostLikeCommand command = new CreatePostLikeCommand(userDetails.getUserId(), postId);
        Long likeId = commandBus.execute(command);

        return ResponseEntity.ok(new ApiResponse<>(
                "SUCCESS",
                "Thích bài viết thành công",
                likeId));
    }

    @DeleteMapping("/likes/{id}")
    public ResponseEntity<ApiResponse<?>> unlikePost(
            @PathVariable Long id,
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        log.info("DELETE /api/posts/likes/{} - userId={}", id, userDetails.getUserId());
        commandBus.execute(new DeletePostLikeCommand(id));

        return ResponseEntity.ok(new ApiResponse<>(
                "SUCCESS",
                "Hủy thích bài viết thành công",
                id));
    }
}
