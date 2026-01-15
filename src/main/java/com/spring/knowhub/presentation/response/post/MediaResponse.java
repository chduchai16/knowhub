package com.spring.knowhub.presentation.response.post;

import com.spring.knowhub.domain.enums.media.MediaType;
import com.spring.knowhub.domain.enums.media.OwnerType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MediaResponse {
    private Long id;
    private String url;
    private MediaType type;
    private OwnerType ownerType;
}
