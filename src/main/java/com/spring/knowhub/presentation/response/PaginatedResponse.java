package com.spring.knowhub.presentation.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PaginatedResponse<T> {
    private List<T> content;         // Dữ liệu
    private PaginationInfo info;     // Thông tin phân trang
}
