package com.spring.knowhub.domain.exceptions.post.tag;

public class DuplicateTagException extends TagDomainException{

    public DuplicateTagException (String message) {
        super("DUPLICATE_TAG_ERROR" , message);
    }

    public DuplicateTagException (String message , Throwable cause) {
        super("DUPLICATE_TAG_ERROR" , message , cause);
    }

    public static DuplicateTagException byName(String name) {
        return new DuplicateTagException("Tag với tên '" + name + "' đã tồn tại.");
    }
}
