package com.spring.knowhub.infrastructure.repositories.jpas.media;

import com.spring.knowhub.infrastructure.entities.media.MediaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaMediaRepository extends JpaRepository<MediaEntity , Long> {
    Boolean existsByUrl(String url);
}
