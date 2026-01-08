package com.spring.knowhub.domain.enums.media;

public enum MediaType {
    IMAGE,
    VIDEO ,
    FILE ;

    public static boolean contains(String value) {
        if (value == null) return false;

        for (MediaType type : values()) {
            if (type.name().equals(value)) {
                return true;
            }
        }
        return false;
    }
}

