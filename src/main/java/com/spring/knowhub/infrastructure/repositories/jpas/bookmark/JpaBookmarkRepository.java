package com.spring.knowhub.infrastructure.repositories.jpas.bookmark;

import com.spring.knowhub.infrastructure.entities.bookmark.BookmarkEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaBookmarkRepository extends JpaRepository<BookmarkEntity , Long> {
}
