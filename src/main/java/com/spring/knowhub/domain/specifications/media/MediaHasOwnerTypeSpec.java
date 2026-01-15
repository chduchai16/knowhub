package com.spring.knowhub.domain.specifications.media;

import com.spring.knowhub.domain.enums.media.OwnerType;
import com.spring.knowhub.domain.models.media.Media;
import com.spring.knowhub.domain.specifications.Specification;

public class MediaHasOwnerTypeSpec implements Specification<Media> {

    private OwnerType ownerType ;

    public MediaHasOwnerTypeSpec(OwnerType ownerType) {
        this.ownerType = ownerType;
    }

    @Override
    public boolean isSatisfiedBy(Media media) {
        return media.getOwnerType().equals(this.ownerType) ;
    }

    public OwnerType getOwnerType() {
        return ownerType;
    }
}
