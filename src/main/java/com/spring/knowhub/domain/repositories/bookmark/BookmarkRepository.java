package com.spring.knowhub.domain.repositories.bookmark;

import com.spring.knowhub.domain.models.bookmark.Bookmark;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface BookmarkRepository {
    Bookmark save(Bookmark bookmark);
    void deleteById(Long id);
    Optional<Bookmark> findById(Long id);
    Page<Bookmark> findBookmarksPaged(Pageable pageable);
}
