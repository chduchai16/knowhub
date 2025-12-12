package com.spring.knowhub.domain.models.user;

public class Permission {
    private Long id ;
    private String code ;

    public Permission(Long id, String code) {
        this.id = id;
        this.code = code;
    }

    public Permission(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
