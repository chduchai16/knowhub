package com.spring.knowhub.infrastructure.entities.notification;

import com.spring.knowhub.infrastructure.entities.BaseEntity;
import com.spring.knowhub.infrastructure.entities.user.UserEntity;
import com.spring.knowhub.domain.enums.notification.NotificationType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "notifications")
@Data
public class NotificationEntity extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NotificationType type;

    @Column(nullable = false, columnDefinition = "NVARCHAR(255)")
    private String title;

    @Column(columnDefinition = "NVARCHAR(MAX)")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "actor_id")
    private UserEntity actor;

    @Column(name = "reference_id")
    private Long referenceId;

    @Column(name = "reference_type", length = 50)
    private String referenceType;

    @Column(name = "post_id")
    private Long postId;
}
