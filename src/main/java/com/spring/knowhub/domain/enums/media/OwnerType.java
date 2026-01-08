package com.spring.knowhub.domain.enums.media;

public enum OwnerType {
    POST,
    USER,
    COMMENT ;

    public static boolean contains(String value) {
        if (value == null) return false;

        for (OwnerType type : values()) {
            if (type.name().equals(value)) {
                return true;
            }
        }
        return false;
    }
}
