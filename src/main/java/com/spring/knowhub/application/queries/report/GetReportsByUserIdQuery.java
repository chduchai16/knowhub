package com.spring.knowhub.application.queries.report;

import com.spring.knowhub.application.buses.Query;
import com.spring.knowhub.domain.models.report.Report;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetReportsByUserIdQuery implements Query<Page<Report>> {
    private Long userId;
    private int page;
    private int size;
}
