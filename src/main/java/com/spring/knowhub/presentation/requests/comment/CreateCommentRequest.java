package com.spring.knowhub.presentation.requests.comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCommentRequest {
    private Long postId;
    private Long parentId;
    private String content;
}
