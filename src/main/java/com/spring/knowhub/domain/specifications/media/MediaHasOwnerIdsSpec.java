package com.spring.knowhub.domain.specifications.media;

import com.spring.knowhub.domain.models.media.Media;
import com.spring.knowhub.domain.specifications.Specification;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class MediaHasOwnerIdsSpec implements Specification<Media> {
    private final List<Long> ownerIds;

    @Override
    public boolean isSatisfiedBy(Media media) {
        return media != null && ownerIds.contains(media.getOwnerId());
    }
}
