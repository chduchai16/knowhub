package com.spring.knowhub.infrastructure.entities.UserAggregate;

import com.spring.knowhub.infrastructure.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "user_followers", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id", "follower_id"})
})
@Data
public class UserFollowerEntity extends BaseEntity {


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "follower_id", nullable = false)
    private UserEntity follower;

}

