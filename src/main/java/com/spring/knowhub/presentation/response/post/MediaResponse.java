package com.spring.knowhub.presentation.response.post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MediaResponse {
    private Long id;
    private String url;
    private String type;
}
