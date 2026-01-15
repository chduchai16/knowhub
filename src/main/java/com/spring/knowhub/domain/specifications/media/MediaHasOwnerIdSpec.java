package com.spring.knowhub.domain.specifications.media;

import com.spring.knowhub.domain.models.media.Media;
import com.spring.knowhub.domain.specifications.Specification;

public class MediaHasOwnerIdSpec implements Specification<Media> {

    private Long ownerId ;

    public MediaHasOwnerIdSpec(Long ownerId) {
        this.ownerId = ownerId;
    }

    @Override
    public boolean isSatisfiedBy(Media media) {
        return media.getOwnerId().equals(this.ownerId) ;
    }

    public Long getOwnerId() {
        return ownerId;
    }
}
