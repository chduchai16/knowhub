package com.spring.knowhub.presentation.response.post;

import com.spring.knowhub.domain.enums.post.PostStatus;
import com.spring.knowhub.domain.enums.post.Privacy;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostResponse {
    private Long id;
    private Long userId;
    private String username;
    private String content;
    private Privacy privacy;
    private PostStatus status;
    private List<TagResponse> tags;
    private List<MediaResponse> medias;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
