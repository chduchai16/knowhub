package com.spring.knowhub.infrastructure.entities.media;

import com.spring.knowhub.domain.enums.media.MediaStatus;
import com.spring.knowhub.domain.enums.media.MediaType;
import com.spring.knowhub.domain.enums.media.OwnerType;
import com.spring.knowhub.infrastructure.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "media")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MediaEntity extends BaseEntity {

    private String publicId ; // cloudinary public _ id
    private String url ; // secure url
    private String originalName ;
    private String format ;
    private Long size ;

    @Enumerated(EnumType.STRING)
    private MediaType type ;

    private String folder ;

    private Long ownerId ; // id thực thể sở hữu

    @Enumerated(EnumType.STRING)
    private OwnerType ownerType ; // loại thực thể sở hữu

    @Enumerated(EnumType.STRING)
    private MediaStatus status ;
}
