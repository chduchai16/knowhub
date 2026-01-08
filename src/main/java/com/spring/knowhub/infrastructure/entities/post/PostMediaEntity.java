package com.spring.knowhub.infrastructure.entities.post;

import com.spring.knowhub.infrastructure.entities.BaseEntity;
import com.spring.knowhub.domain.enums.MediaType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "post_medias")
@Data
public class PostMediaEntity extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private PostEntity post;
    
    @Column(nullable = false, columnDefinition = "NVARCHAR(MAX)")
    private String url;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MediaType type;

}

