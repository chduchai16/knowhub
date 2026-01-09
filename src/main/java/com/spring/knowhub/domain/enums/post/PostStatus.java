package com.spring.knowhub.domain.enums.post;

public enum PostStatus {
    DRAFT, // bản nháp
    PUBLISHED, // đã xuất bản
    DELETED; // đã xóa

    public static boolean contains(String test) {
        for (PostStatus status : PostStatus.values()) {
            if (status.name().equals(test)) {
                return true;
            }
        }
        return false;
    }
}
