package com.spring.knowhub.application.queries.user.user;

import com.spring.knowhub.application.buses.QueryHandler;
import com.spring.knowhub.application.exceptions.user.user.GetUserQueryException;
import com.spring.knowhub.domain.exceptions.user.user.UserNotFoundException;
import com.spring.knowhub.domain.models.user.User;
import com.spring.knowhub.domain.repositories.user.UserRepository;
import com.spring.knowhub.infrastructure.exceptions.user.UserRepositoryException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class GetUserByIdQueryHandler implements QueryHandler<GetUserByIdQuery, User> {

    private final UserRepository userRepository;

    @Override
    public User handle(GetUserByIdQuery query) {
        log.debug("Bắt đầu lấy thông tin user với ID: {}", query.getUserId());

        try {
            validateQuery(query);

            Optional<User> user = userRepository.findById(query.getUserId());
            if (user.isEmpty()) {
                log.warn("User không tồn tại với ID: {}", query.getUserId());
                throw UserNotFoundException.byId(query.getUserId());
            }

            log.debug("Lấy thông tin user thành công với ID: {}", query.getUserId());
            return user.get();

        } catch (UserNotFoundException ex) {
            log.warn("User không tồn tại: {}", ex.getMessage());
            throw new GetUserQueryException(
                    "User không tồn tại: " + ex.getMessage(),
                    ex
            );

        } catch (UserRepositoryException ex) {
            log.error("Lỗi database khi lấy user: {}", ex.getMessage(), ex);
            throw new GetUserQueryException(
                    "Lỗi lấy user từ database: " + ex.getMessage(),
                    ex
            );

        } catch (Exception ex) {
            log.error("Lỗi không mong muốn khi lấy user", ex);
            throw new GetUserQueryException(
                    "Lỗi không mong muốn: " + ex.getMessage(),
                    ex
            );
        }
    }

    private void validateQuery(GetUserByIdQuery query) {
        if (query == null) {
            throw GetUserQueryException.missingRequiredField("query");
        }

        if (query.getUserId() == null || query.getUserId() <= 0) {
            throw GetUserQueryException.missingRequiredField("userId");
        }
    }

    @Override
    public boolean supports(Object query) {
        return query instanceof GetUserByIdQuery;
    }
}
