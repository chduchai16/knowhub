package com.spring.knowhub.presentation.requests.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateMessageRequest {
    private Long receiverId;
    private String content;
    private List<Long> mediaIds;
}
