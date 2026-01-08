package com.spring.knowhub.domain.enums.post;

public enum Privacy {
    PUBLIC,
    PRIVATE,
    FRIENDS_ONLY ;

    public static boolean contains(String test) {
        for (Privacy p : Privacy.values()) {
            if (p.name().equals(test)) {
                return true;
            }
        }
        return false;
    }
}

