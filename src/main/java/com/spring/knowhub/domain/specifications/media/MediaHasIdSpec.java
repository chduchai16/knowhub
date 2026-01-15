package com.spring.knowhub.domain.specifications.media;

import com.spring.knowhub.domain.models.media.Media;
import com.spring.knowhub.domain.specifications.Specification;

public class MediaHasIdSpec implements Specification<Media> {
    private Long id ;

    public MediaHasIdSpec(Long id) {
        this.id = id;
    }

    @Override
    public boolean isSatisfiedBy(Media media) {
        return media.getId().equals(this.id) ;
    }

    public Long getId() {
        return id;
    }
}
