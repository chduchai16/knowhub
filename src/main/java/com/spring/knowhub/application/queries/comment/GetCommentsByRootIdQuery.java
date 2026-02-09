package com.spring.knowhub.application.queries.comment;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetCommentsByRootIdQuery {
    private Long rootId;
    private int page;
    private int size;
}
