package com.spring.knowhub.domain.repositories.bookmark;

import com.spring.knowhub.domain.models.bookmark.Bookmark;

import java.util.Optional;

public interface BookmarkCommandRepository {
    Optional<Bookmark> save(Bookmark bookmark);
    void deleteById(Long id);
}
