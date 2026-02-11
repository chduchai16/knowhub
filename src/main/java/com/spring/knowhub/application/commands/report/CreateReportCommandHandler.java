package com.spring.knowhub.application.commands.report;

import com.spring.knowhub.application.buses.CommandHandler;
import com.spring.knowhub.application.validators.report.CreateReportValidator;
import com.spring.knowhub.domain.enums.report.ReportStatus;
import com.spring.knowhub.domain.exceptions.comment.CommentNotFoundException;
import com.spring.knowhub.domain.exceptions.post.post.PostNotFoundException;
import com.spring.knowhub.domain.exceptions.report.DuplicateReportException;
import com.spring.knowhub.domain.exceptions.report.InvalidReportException;
import com.spring.knowhub.domain.exceptions.user.user.UserNotFoundException;
import com.spring.knowhub.domain.models.comment.Comment;
import com.spring.knowhub.domain.models.post.Post;
import com.spring.knowhub.domain.models.report.Report;
import com.spring.knowhub.domain.models.user.User;
import com.spring.knowhub.domain.repositories.comment.CommentRepository;
import com.spring.knowhub.domain.repositories.post.PostRepository;
import com.spring.knowhub.domain.repositories.report.ReportRepository;
import com.spring.knowhub.domain.repositories.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Transactional
public class CreateReportCommandHandler implements CommandHandler<CreateReportCommand, Long> {
    private final ReportRepository reportRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    @Override
    public boolean supports(Object command) {
        return command instanceof CreateReportCommand;
    }

    @Override
    public Long handle(CreateReportCommand command) {
        CreateReportValidator.validate(command);

        User reporter = userRepository.findById(command.getReporterId())
                .orElseThrow(() -> UserNotFoundException.byId(command.getReporterId()));

        Report report = new Report();
        report.setReporter(reporter);
        report.setReportType(command.getReportType());
        report.setDescription(command.getDescription());
        report.setStatus(ReportStatus.PENDING);

        if ("POST".equalsIgnoreCase(command.getReportedEntityType())) {
            Post post = postRepository.findById(command.getReportedEntityId())
                    .orElseThrow(() -> PostNotFoundException.withId(command.getReportedEntityId()));

            if (post.getUser().getId().equals(command.getReporterId())) {
                throw InvalidReportException.cannotReportOwnContent();
            }

            if (reportRepository.existsByReporterIdAndPostId(command.getReporterId(), command.getReportedEntityId())) {
                throw DuplicateReportException.forEntity("POST", command.getReportedEntityId());
            }

            report.setPost(post);
        } else if ("COMMENT".equalsIgnoreCase(command.getReportedEntityType())) {
            Comment comment = commentRepository.findById(command.getReportedEntityId())
                    .orElseThrow(() -> CommentNotFoundException.withId(command.getReportedEntityId()));

            if (comment.getUser().getId().equals(command.getReporterId())) {
                throw InvalidReportException.cannotReportOwnContent();
            }

            if (reportRepository.existsByReporterIdAndCommentId(command.getReporterId(),
                    command.getReportedEntityId())) {
                throw DuplicateReportException.forEntity("COMMENT", command.getReportedEntityId());
            }

            report.setComment(comment);
        } else if ("USER".equalsIgnoreCase(command.getReportedEntityType())) {
            User reportedUser = userRepository.findById(command.getReportedEntityId())
                    .orElseThrow(() -> UserNotFoundException.byId(command.getReportedEntityId()));

            if (reportedUser.getId().equals(command.getReporterId())) {
                throw InvalidReportException.cannotReportOwnContent();
            }

            if (reportRepository.existsByReporterIdAndReportedUserId(command.getReporterId(),
                    command.getReportedEntityId())) {
                throw DuplicateReportException.forEntity("USER", command.getReportedEntityId());
            }

            report.setReportedUser(reportedUser);
        }

        Report savedReport = reportRepository.save(report);
        return savedReport.getId();
    }
}
