package com.spring.knowhub.domain.specifications.media;

import com.spring.knowhub.domain.models.media.Media;
import com.spring.knowhub.domain.specifications.AlwaysTrueSpecification;
import com.spring.knowhub.domain.specifications.Specification;

public class MediaSpecifications {

    public static Specification<Media> hasOwnerId (Long ownerId) {
        return ownerId == null
                ? new AlwaysTrueSpecification()
                : new MediaHasOwnerIdSpec(ownerId) ;
    }

    public static Specification<Media> hasOwnerType (Enum ownerType) {
        return ownerType == null
                ? new AlwaysTrueSpecification()
                : new MediaHasOwnerTypeSpec((com.spring.knowhub.domain.enums.media.OwnerType) ownerType) ;
    }

    public static Specification<Media> hasId (Long id) {
        return id == null
                ? new AlwaysTrueSpecification()
                : new MediaHasIdSpec(id) ;
    }

}
