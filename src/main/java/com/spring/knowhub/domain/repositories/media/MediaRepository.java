package com.spring.knowhub.domain.repositories.media;

import com.spring.knowhub.domain.models.media.Media;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface MediaRepository {
    Media save (Media media) ;
    List<Media> saveAll (List<Media> mediaList) ;
    Page<Media> findPagedMedia(Pageable pageable) ;
    Optional<Media> findMediaById(Long id) ;
    Media updateMedia(Media media) ;
    void deleteMediaById(Long id) ;
    Boolean existsByUrl(String url) ;
}
