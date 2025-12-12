package com.spring.knowhub.infrastructure.entities.post;

import com.spring.knowhub.infrastructure.entities.BaseEntity;
import com.spring.knowhub.infrastructure.entities.user.UserEntity;
import com.spring.knowhub.domain.enums.Privacy;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "posts")
@Data
public class PostEntity extends BaseEntity {


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Privacy privacy = Privacy.PUBLIC;

    @Column(nullable = false)
    private Boolean isDeleted = false;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PostMediaEntity> media = new ArrayList<>();

}

