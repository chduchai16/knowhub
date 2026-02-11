package com.spring.knowhub.domain.models.report;

import com.spring.knowhub.domain.enums.report.ReportStatus;
import com.spring.knowhub.domain.enums.report.ReportType;
import com.spring.knowhub.domain.models.BaseModel;
import com.spring.knowhub.domain.models.comment.Comment;
import com.spring.knowhub.domain.models.post.Post;
import com.spring.knowhub.domain.models.user.User;

public class Report extends BaseModel {
    private Long id;
    private User reporter;
    private Post post;
    private Comment comment;
    private ReportType reportType;
    private String description;
    private ReportStatus status;

    public Report() {
    }

    public Report(Long id, User reporter, Post post, Comment comment, ReportType reportType, String description,
            ReportStatus status) {
        this.id = id;
        this.reporter = reporter;
        this.post = post;
        this.comment = comment;
        this.reportType = reportType;
        this.description = description;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getReporter() {
        return reporter;
    }

    public void setReporter(User reporter) {
        this.reporter = reporter;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public ReportType getReportType() {
        return reportType;
    }

    public void setReportType(ReportType reportType) {
        this.reportType = reportType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ReportStatus getStatus() {
        return status;
    }

    public void setStatus(ReportStatus status) {
        this.status = status;
    }
}
