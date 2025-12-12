package com.spring.knowhub.domain.models.user;

import java.util.Set;

public class Role {
    private Long id;
    private String name ;
    private Set<Permission> permissions;

    public Role(Long id, String name, Set<Permission> permissions) {
        this.id = id;
        this.name = name;
        this.permissions = permissions;
    }

    public Role(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }
}
