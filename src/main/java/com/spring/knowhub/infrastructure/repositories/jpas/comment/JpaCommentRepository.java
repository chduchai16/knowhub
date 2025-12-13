package com.spring.knowhub.infrastructure.repositories.jpas.comment;

import com.spring.knowhub.infrastructure.entities.comment.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaCommentRepository extends JpaRepository<CommentEntity , Long> {
}
