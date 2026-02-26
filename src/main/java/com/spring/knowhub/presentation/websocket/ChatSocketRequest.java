package com.spring.knowhub.presentation.websocket;

import java.util.List;

import lombok.Data;

@Data
public class ChatSocketRequest {
    private Long receiverId;
    private String content;
    private List<Long> mediaIds;
}