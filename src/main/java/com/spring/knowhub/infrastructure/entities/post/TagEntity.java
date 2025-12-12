package com.spring.knowhub.infrastructure.entities.post;

import jakarta.persistence.*;
import com.spring.knowhub.infrastructure.entities.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "tags")
@Data
public class TagEntity extends BaseEntity {


    @Column(unique = true, nullable = false)
    private String name;

}

