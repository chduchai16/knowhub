package com.spring.knowhub.domain.models.user;

import com.spring.knowhub.domain.models.BaseModel;

public class Permission extends BaseModel {
    private Long id ;
    private String code ;
    private String description ;

    public Permission(Long id, String code , String description){
        this.id = id;
        this.code = code;
        this.description = description ;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
