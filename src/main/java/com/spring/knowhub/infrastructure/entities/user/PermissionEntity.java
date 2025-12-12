package com.spring.knowhub.infrastructure.entities.user;

import jakarta.persistence.*;
import com.spring.knowhub.infrastructure.entities.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode(callSuper = true)
@Getter
@Entity
@Table(name = "permissions")
@Data
public class PermissionEntity extends BaseEntity {


    @Column(unique = true, nullable = false)
    private String code;

}

