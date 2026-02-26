package com.spring.knowhub.infrastructure.entities.message;

import com.spring.knowhub.infrastructure.entities.BaseEntity;
import com.spring.knowhub.infrastructure.entities.user.UserEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "messages")
@Data
public class MessageEntity extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id", nullable = false)
    private UserEntity sender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver_id", nullable = false)
    private UserEntity receiver;

    @Column(columnDefinition = "NVARCHAR(MAX)")
    private String content;

    @Column(nullable = false)
    private Boolean isDeleted = false;
}
