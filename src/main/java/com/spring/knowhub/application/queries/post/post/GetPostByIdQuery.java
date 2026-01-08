package com.spring.knowhub.application.queries.post.post;

import com.spring.knowhub.application.buses.Query;
import com.spring.knowhub.domain.models.post.Post;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetPostByIdQuery implements Query<Post> {
    private Long id ;
}
