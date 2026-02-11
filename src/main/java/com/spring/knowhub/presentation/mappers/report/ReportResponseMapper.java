package com.spring.knowhub.presentation.mappers.report;

import com.spring.knowhub.domain.models.report.Report;
import com.spring.knowhub.infrastructure.configurations.ModelMapperConfiguration;
import com.spring.knowhub.presentation.response.report.ReportResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReportResponseMapper {
    private final ModelMapperConfiguration modelMapper;
    private TypeMap<Report, ReportResponse> fromReportToReportResponseTypeMap;

    public ReportResponse fromDomainToResponse(Report report) {
        if (report == null) {
            return null;
        }

        if (fromReportToReportResponseTypeMap == null) {
            fromReportToReportResponseTypeMap = modelMapper.modelMapper().createTypeMap(Report.class,
                    ReportResponse.class);
            fromReportToReportResponseTypeMap.addMappings(mapper -> {
                mapper.skip(ReportResponse::setReporterId);
                mapper.skip(ReportResponse::setReporterUsername);
                mapper.skip(ReportResponse::setReportedEntityType);
                mapper.skip(ReportResponse::setReportedEntityId);
            });
            fromReportToReportResponseTypeMap.implicitMappings();
        }

        ReportResponse response = fromReportToReportResponseTypeMap.map(report);

        if (report.getReporter() != null) {
            response.setReporterId(report.getReporter().getId());
            response.setReporterUsername(report.getReporter().getUsername());
        }

        if (report.getPost() != null) {
            response.setReportedEntityType("POST");
            response.setReportedEntityId(report.getPost().getId());
        } else if (report.getComment() != null) {
            response.setReportedEntityType("COMMENT");
            response.setReportedEntityId(report.getComment().getId());
        } else if (report.getReportedUser() != null) {
            response.setReportedEntityType("USER");
            response.setReportedEntityId(report.getReportedUser().getId());
        }

        return response;
    }
}
