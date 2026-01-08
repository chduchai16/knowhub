package com.spring.knowhub.presentation.requests.post.post;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CreatePostRequest {
    private String content;
    private String privacy;
    private List<Long> mediaIds;
    private List<Long> tagIds;
    private String status;
}
