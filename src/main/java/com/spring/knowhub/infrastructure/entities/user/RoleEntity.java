package com.spring.knowhub.infrastructure.entities.user;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;
import com.spring.knowhub.infrastructure.entities.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "roles")
@Data
public class RoleEntity extends BaseEntity {


    @Column(unique = true, nullable = false, columnDefinition = "NVARCHAR(MAX)")
    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "role_permissions",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id")
    )
    private Set<PermissionEntity> permissions = new HashSet<>();
}

