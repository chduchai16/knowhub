package com.spring.knowhub.application.queries.user.user;

import com.spring.knowhub.application.buses.QueryHandler;
import com.spring.knowhub.application.exceptions.user.user.GetUserException;
import com.spring.knowhub.domain.models.user.User;
import com.spring.knowhub.domain.repositories.user.UserRepository;
import com.spring.knowhub.infrastructure.exceptions.user.user.UserRepositoryException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class GetUsersPagedQueryHandler implements QueryHandler<GetUsersPagedQuery, Page<User>> {

    private final UserRepository userRepository;

    @Override
    public Page<User> handle(GetUsersPagedQuery query) {
        log.debug("Bắt đầu lấy danh sách user với page: {}, size: {}",query.getPageable().getPageNumber(),query.getPageable().getPageSize());

        try {
            validateQuery(query);

            Page<User> users = userRepository.findUsersPaged(query.getPageable());

            log.debug("Lấy danh sách user thành công, total: {}", users.getTotalElements());
            return users;

        } catch (UserRepositoryException ex) {
            log.error("Lỗi database khi lấy danh sách user: {}", ex.getMessage(), ex);
            throw new GetUserException(
                    "Lỗi lấy danh sách user từ database: " + ex.getMessage(),
                    ex
            );

        } catch (Exception ex) {
            log.error("Lỗi không mong muốn khi lấy danh sách user", ex);
            throw new GetUserException(
                    "Lỗi không mong muốn: " + ex.getMessage(),
                    ex
            );
        }
    }

    private void validateQuery(GetUsersPagedQuery query) {
        if (query == null) {
            throw GetUserException.missingRequiredField("query");
        }

        if (query.getPageable() == null) {
            throw GetUserException.missingRequiredField("pageable");
        }
    }

    @Override
    public boolean supports(Object query) {
        return query instanceof GetUsersPagedQuery;
    }
}
