package com.spring.knowhub.domain.exceptions.post.tag;

public class TagNotFoundException extends TagDomainException{

    public TagNotFoundException (String message) {
        super("TAG_NOT_FOUND_ERROR" , message);
    }

    public TagNotFoundException (String message, Throwable cause) {
        super("TAG_NOT_FOUND_ERROR" , message, cause);
    }

    public static TagNotFoundException byId(Long id) {
        return new TagNotFoundException("Không tìm thấy tag với id: " + id);
    }

}
