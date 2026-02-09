package com.spring.knowhub.application.queries.comment;

import com.spring.knowhub.application.buses.Query;
import com.spring.knowhub.domain.models.comment.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Data
@AllArgsConstructor
public class GetCommentsByPostIdQuery implements Query<Page<Comment>> {
    private Long postId;
    private Pageable pageable;
}
