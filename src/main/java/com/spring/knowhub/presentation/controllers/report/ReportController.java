package com.spring.knowhub.presentation.controllers.report;

import com.spring.knowhub.application.buses.CommandBus;
import com.spring.knowhub.application.buses.QueryBus;
import com.spring.knowhub.application.commands.report.CreateReportCommand;
import com.spring.knowhub.application.queries.report.GetReportsByUserIdQuery;
import com.spring.knowhub.domain.models.report.Report;
import com.spring.knowhub.infrastructure.security.CustomUserDetails;
import com.spring.knowhub.presentation.mappers.report.ReportResponseMapper;
import com.spring.knowhub.presentation.requests.report.CreateReportRequest;
import com.spring.knowhub.presentation.response.ApiResponse;
import com.spring.knowhub.presentation.response.PaginatedResponse;
import com.spring.knowhub.presentation.response.PaginationInfo;
import com.spring.knowhub.presentation.response.report.ReportResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import com.spring.knowhub.application.commands.report.DeleteReportCommand;
import com.spring.knowhub.application.commands.report.UpdateReportCommand;
import com.spring.knowhub.application.queries.report.GetPagedReportsQuery;
import com.spring.knowhub.domain.enums.report.ReportStatus;
import com.spring.knowhub.domain.enums.report.ReportType;
import com.spring.knowhub.presentation.requests.report.UpdateReportRequest;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/reports")
@RestController
@RequiredArgsConstructor
public class ReportController {

        private final CommandBus commandBus;
        private final QueryBus queryBus;
        private final ReportResponseMapper reportResponseMapper;

        @PostMapping
        public ResponseEntity<ApiResponse<Long>> createReport(
                        @RequestBody CreateReportRequest request,
                        @AuthenticationPrincipal CustomUserDetails userDetails) {

                CreateReportCommand command = new CreateReportCommand(
                                userDetails.getUserId(),
                                request.getReportedEntityType(),
                                request.getReportedEntityId(),
                                request.getReportType(),
                                request.getDescription());

                Long reportId = commandBus.execute(command);

                return ResponseEntity.ok(new ApiResponse<>(
                                "SUCCESS",
                                "Report đã được tạo thành công",
                                reportId));
        }

        @GetMapping("/my-reports")
        public ResponseEntity<ApiResponse<PaginatedResponse<ReportResponse>>> getMyReports(
                        @RequestParam(defaultValue = "0") int page,
                        @RequestParam(defaultValue = "10") int limit,
                        @AuthenticationPrincipal CustomUserDetails userDetails) {

                GetReportsByUserIdQuery query = new GetReportsByUserIdQuery(
                                userDetails.getUserId(),
                                page,
                                limit);

                Page<Report> reportPage = queryBus.execute(query);
                Page<ReportResponse> responsePage = reportPage.map(reportResponseMapper::fromDomainToResponse);

                PaginatedResponse<ReportResponse> paginatedResponse = new PaginatedResponse<ReportResponse>(
                                responsePage.getContent(),
                                new PaginationInfo(
                                                responsePage.getTotalElements(),
                                                responsePage.getTotalPages(),
                                                responsePage.getNumber(),
                                                responsePage.getSize()));

                return ResponseEntity.ok(new ApiResponse<>(
                                "SUCCESS",
                                "Lấy danh sách reports thành công",
                                paginatedResponse));
        }

        @GetMapping
        public ResponseEntity<ApiResponse<PaginatedResponse<ReportResponse>>> getAllReports(
                        @RequestParam(defaultValue = "0") int page,
                        @RequestParam(defaultValue = "10") int limit,
                        @RequestParam(required = false) String keyword,
                        @RequestParam(required = false) ReportType type,
                        @RequestParam(required = false) ReportStatus status) {

                GetPagedReportsQuery query = new GetPagedReportsQuery(page, limit, keyword, type, status);
                Page<Report> reportPage = queryBus.execute(query);
                Page<ReportResponse> responsePage = reportPage.map(reportResponseMapper::fromDomainToResponse);

                PaginatedResponse<ReportResponse> paginatedResponse = new PaginatedResponse<ReportResponse>(
                                responsePage.getContent(),
                                new PaginationInfo(
                                                responsePage.getTotalElements(),
                                                responsePage.getTotalPages(),
                                                responsePage.getNumber(),
                                                responsePage.getSize()));

                return ResponseEntity.ok(new ApiResponse<>(
                                "SUCCESS",
                                "Lấy danh sách reports thành công",
                                paginatedResponse));
        }

        @PutMapping
        public ResponseEntity<ApiResponse<Long>> updateReport(@RequestBody UpdateReportRequest request) {
                UpdateReportCommand command = new UpdateReportCommand(
                                request.getId(),
                                request.getStatus(),
                                request.getDescription());
                Long reportId = commandBus.execute(command);
                return ResponseEntity.ok(new ApiResponse<>(
                                "SUCCESS",
                                "Cập nhật report thành công",
                                reportId));
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<ApiResponse<Void>> deleteReport(@PathVariable Long id) {
                DeleteReportCommand command = new DeleteReportCommand(id);
                commandBus.execute(command);
                return ResponseEntity.ok(new ApiResponse<>(
                                "SUCCESS",
                                "Xóa report thành công",
                                null));
        }
}
