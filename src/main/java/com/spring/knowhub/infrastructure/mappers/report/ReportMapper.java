package com.spring.knowhub.infrastructure.mappers.report;

import com.spring.knowhub.domain.models.report.Report;
import com.spring.knowhub.infrastructure.entities.comment.CommentEntity;
import com.spring.knowhub.infrastructure.entities.post.PostEntity;
import com.spring.knowhub.infrastructure.entities.report.ReportEntity;
import com.spring.knowhub.infrastructure.entities.user.UserEntity;
import com.spring.knowhub.infrastructure.mappers.comment.CommentMapper;
import com.spring.knowhub.infrastructure.mappers.post.PostMapper;
import com.spring.knowhub.infrastructure.mappers.user.UserMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReportMapper {

    private final ModelMapper modelMapper ;
    private final UserMapper userMapper ;
    private final PostMapper postMapper ;
    private final CommentMapper commentMapper ;
    private TypeMap<Report , ReportEntity> fromDomainToEntityTypeMap ;
    private TypeMap<ReportEntity , Report> fromEntityToDomainTypeMap ;

    public ReportEntity fromDomainToEntity(Report report){
        if (fromDomainToEntityTypeMap == null) {
            fromDomainToEntityTypeMap = modelMapper.createTypeMap(Report.class, ReportEntity.class);
            fromDomainToEntityTypeMap.getMappings().clear();
            fromDomainToEntityTypeMap.addMappings(mapper -> {
                mapper.skip(ReportEntity :: setReporter);
                mapper.skip(ReportEntity :: setPost);
                mapper.skip(ReportEntity :: setComment);
            });
            fromDomainToEntityTypeMap.implicitMappings();
        }
        ReportEntity reportEntity = fromDomainToEntityTypeMap.map(report);

        // map reporter
        if(report.getReporter() != null){
            UserEntity reporterEntity = userMapper.fromDomainToEntity(report.getReporter());
            reportEntity.setReporter(reporterEntity);
        }

        // map post
        if(report.getPost() != null){
            PostEntity postEntity = postMapper.fromDomainToEntity(report.getPost());
            reportEntity.setPost(postEntity);
        }

        // map comment
        if(report.getComment() != null){
            CommentEntity commentEntity = commentMapper.fromDomainToEntity(report.getComment());
            reportEntity.setComment(commentEntity);
        }

        return reportEntity;
    }

    public Report fromEntityToDomain(ReportEntity reportEntity){
        if (fromEntityToDomainTypeMap == null) {
            fromEntityToDomainTypeMap = modelMapper.createTypeMap(ReportEntity.class, Report.class);
            fromEntityToDomainTypeMap.getMappings().clear();
            fromEntityToDomainTypeMap.addMappings(mapper -> {
                mapper.skip(Report :: setReporter);
                mapper.skip(Report :: setPost);
                mapper.skip(Report :: setComment);
            });
            fromEntityToDomainTypeMap.implicitMappings();
        }
        Report report = fromEntityToDomainTypeMap.map(reportEntity);

        // map reporter
        if(reportEntity.getReporter() != null){
            report.setReporter(userMapper.fromEntityToDomain(reportEntity.getReporter()));
        }

        // map post
        if(reportEntity.getPost() != null){
            report.setPost(postMapper.fromEntityToDomain(reportEntity.getPost()));
        }

        // map comment
        if(reportEntity.getComment() != null){
            report.setComment(commentMapper.fromEntityToDomain(reportEntity.getComment()));
        }
        return report;
    }

}
