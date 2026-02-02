package com.spring.knowhub.application.queries.post.post;

import com.spring.knowhub.application.buses.Query;
import com.spring.knowhub.domain.models.post.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Data
@AllArgsConstructor
public class GetNewFeedsQuery implements Query<Page<Post>> {
    private Pageable pageable ;
}
