package com.spring.knowhub.infrastructure.entities.post;

import com.spring.knowhub.infrastructure.entities.BaseEntity;
import com.spring.knowhub.infrastructure.entities.user.UserEntity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "post_likes", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"post_id", "user_id"})
})
@Data
public class PostLikeEntity extends BaseEntity {


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private PostEntity post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;
}

