package com.spring.knowhub.infrastructure.entities.post;

import com.spring.knowhub.infrastructure.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "post_tags", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"post_id", "tag_id"})
})
@Data
public class PostTagEntity extends BaseEntity {


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private PostEntity post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tag_id", nullable = false)
    private TagEntity tag;
}

