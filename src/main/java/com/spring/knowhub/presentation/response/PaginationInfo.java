package com.spring.knowhub.presentation.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PaginationInfo {
    private long totalElements;
    private int totalPages;
    private int currentPage;
    private int pageSize;
}
