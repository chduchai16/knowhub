package com.spring.knowhub.application.queries.post.tag;

import com.spring.knowhub.application.buses.Query;
import com.spring.knowhub.domain.models.post.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Data
@AllArgsConstructor
public class GetTagPagedQuery implements Query<Page<Tag>> {
    private Pageable pageable;
}
