package com.spring.knowhub.application.commands.user.user;

import com.spring.knowhub.application.buses.CommandHandler;
import com.spring.knowhub.application.exceptions.user.user.DeleteUserCommandException;
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
public class DeleteUserCommandHandler implements CommandHandler<DeleteUserCommand, Long> {

    private final UserRepository userRepository;

    @Override
    public Long handle(DeleteUserCommand command) {
        log.info("Bắt đầu xóa user với ID: {}", command.getUserId());

        try {
            validateCommand(command);

            // Kiểm tra user có tồn tại không
            Optional<User> existingUser = userRepository.findById(command.getUserId());
            if (existingUser.isEmpty()) {
                log.warn("User không tồn tại với ID: {}", command.getUserId());
                throw UserNotFoundException.byId(command.getUserId());
            }

            Long userId = command.getUserId();

            // Xóa user
            userRepository.deleteById(userId);

            log.info("Xóa user thành công với ID: {}", userId);
            return userId;

        } catch (UserNotFoundException ex) {
            log.warn("User không tồn tại: {}", ex.getMessage());
            throw new DeleteUserCommandException(
                    "User không tồn tại: " + ex.getMessage(),
                    ex
            );

        } catch (UserRepositoryException ex) {
            log.error("Lỗi database khi xóa user: {}", ex.getMessage(), ex);
            throw new DeleteUserCommandException(
                    "Lỗi xóa user từ database: " + ex.getMessage(),
                    ex
            );

        } catch (Exception ex) {
            log.error("Lỗi không mong muốn khi xóa user", ex);
            throw new DeleteUserCommandException(
                    "Lỗi không mong muốn: " + ex.getMessage(),
                    ex
            );
        }
    }

    private void validateCommand(DeleteUserCommand command) {
        if (command == null) {
            throw DeleteUserCommandException.missingRequiredField("command");
        }

        if (command.getUserId() == null || command.getUserId() <= 0) {
            throw DeleteUserCommandException.missingRequiredField("userId");
        }
    }

    @Override
    public boolean supports(Object command) {
        return command instanceof DeleteUserCommand;
    }
}
