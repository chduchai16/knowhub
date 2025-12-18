package com.spring.knowhub.application.queries.user.user;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.spring.knowhub.application.buses.QueryHandler;
import com.spring.knowhub.application.exceptions.user.user.GetUserException;
import com.spring.knowhub.domain.exceptions.user.user.UserNotFoundException;
import com.spring.knowhub.domain.models.user.User;
import com.spring.knowhub.domain.repositories.user.UserRepository;
import com.spring.knowhub.infrastructure.exceptions.user.user.UserRepositoryException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Query Handler để lấy thông tin user theo username
 * 
 * Business Logic:
 * 1. Tìm user theo username
 * 2. Return user info
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class GetUserByUsernameQueryHandler implements QueryHandler<GetUserByUsernameQuery, User> {

    private final UserRepository userRepository;

    @Override
    public User handle(GetUserByUsernameQuery query) {
        log.debug("Bắt đầu lấy thông tin user với username: {}", query.getUsername());

        try {
            validateQuery(query);

            Optional<User> user = userRepository.findByUsername(query.getUsername());
            if (user.isEmpty()) {
                log.warn("User không tồn tại với username: {}", query.getUsername());
                throw UserNotFoundException.byUsername(query.getUsername());
            }

            log.debug("Lấy thông tin user thành công với username: {}", query.getUsername());
            return user.get();
        } catch (UserNotFoundException ex) {
            log.warn("User không tồn tại: {}", ex.getMessage());
            throw ex;
        } catch (UserRepositoryException ex) {
            log.error("Lỗi database khi lấy user: {}", ex.getMessage(), ex);
            throw new GetUserException(
                    "Lỗi lấy user từ database: " + ex.getMessage(),
                    ex
            );
        } catch (Exception ex) {
            log.error("Lỗi không mong muốn khi lấy user", ex);
            throw new GetUserException(
                    "Lỗi không mong muốn: " + ex.getMessage(),
                    ex
            );
        }
    }

    private void validateQuery(GetUserByUsernameQuery query) {
        if (query == null) {
            throw GetUserException.missingRequiredField("query");
        }

        if (query.getUsername() == null || query.getUsername().trim().isEmpty()) {
            throw GetUserException.missingRequiredField("username");
        }
    }

    @Override
    public boolean supports(Object query) {
        return query instanceof GetUserByUsernameQuery;
    }
}
