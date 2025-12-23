package com.spring.knowhub.infrastructure.entities.activitylog;

import com.spring.knowhub.infrastructure.entities.BaseEntity;
import com.spring.knowhub.infrastructure.entities.user.UserEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "activity_logs")
@Data
public class ActivityLogEntity extends BaseEntity {


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @Column(nullable = false, columnDefinition = "NVARCHAR(MAX)")
    private String action;

    @Column(nullable = false, columnDefinition = "NVARCHAR(MAX)")
    private String targetType;

    @Column(nullable = false)
    private Long targetId;

    @Column(columnDefinition = "NVARCHAR(MAX)")
    private String metadata;
}

