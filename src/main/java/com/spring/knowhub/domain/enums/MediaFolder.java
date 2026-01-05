package com.spring.knowhub.domain.enums;

public enum MediaFolder {
    POST("posts"),
    USER("users"),
    COMMENT("comments"),
    TEMP("temp");

    private final String path;

    MediaFolder(String path) {
        this.path = path;
    }

    public String path() {
        return path;
    }
}
