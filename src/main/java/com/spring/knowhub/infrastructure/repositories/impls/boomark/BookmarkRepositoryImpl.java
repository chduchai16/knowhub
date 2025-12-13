package com.spring.knowhub.infrastructure.repositories.impls.boomark;

import com.spring.knowhub.domain.models.bookmark.Bookmark;
import com.spring.knowhub.domain.repositories.bookmark.BookmarkRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public class BookmarkRepositoryImpl implements BookmarkRepository {
    @Override
    public Optional<Bookmark> save(Bookmark bookmark) {
        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public Optional<Bookmark> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Page<Bookmark> findBookmarksPaged(Pageable pageable) {
        return null;
    }
}
