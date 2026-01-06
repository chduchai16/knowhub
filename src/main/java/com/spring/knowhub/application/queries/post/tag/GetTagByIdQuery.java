package com.spring.knowhub.application.queries.post.tag;

import com.spring.knowhub.application.buses.Query;
import com.spring.knowhub.domain.models.post.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetTagByIdQuery implements Query<Tag> {
    private Long id ;
}
