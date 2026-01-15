package com.spring.knowhub.infrastructure.repositories.jpas.media;

import com.spring.knowhub.infrastructure.entities.media.MediaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface JpaMediaRepository extends JpaRepository<MediaEntity, Long>, JpaSpecificationExecutor<MediaEntity> {
    Boolean existsByUrl(String url);
}
