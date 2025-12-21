package com.spring.knowhub.application.commands.user.user;

import java.util.Optional;

import com.spring.knowhub.application.buses.CommandHandler;
import com.spring.knowhub.application.exceptions.user.user.UpdateUserException;
import com.spring.knowhub.domain.exceptions.user.user.InvalidUserException;
import com.spring.knowhub.domain.exceptions.user.user.UserNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import com.spring.knowhub.domain.models.user.User;
import com.spring.knowhub.domain.repositories.user.UserRepository;
import com.spring.knowhub.infrastructure.exceptions.user.user.UserRepositoryException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class UpdateUserCommandHandler implements CommandHandler<UpdateUserCommand, Long> {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Long handle(UpdateUserCommand command) {
        log.info("Bắt đầu thực hiện cập nhật user với ID: {}", command.getUserId());
        try {
            validateCommand(command);
            Optional<User> existingUser = userRepository.findById(command.getUserId());
            if (existingUser.isEmpty()) {
                log.warn("User không tồn tại với ID: {}", command.getUserId());
                throw UserNotFoundException.byId(command.getUserId());
            }
            User user = existingUser.get();
            if (command.getFullName() != null && !command.getFullName().trim().isEmpty()) {
                user.setFullName(command.getFullName());
            }
            if (command.getBio() != null) {
                user.setBio(command.getBio());
            }
            if (command.getAvatarUrl() != null) {
                user.setAvatarUrl(command.getAvatarUrl());
            }

            Optional<User> updatedUser = userRepository.save(user);

            log.info("Cập nhật user thành công với ID: {}", command.getUserId());
            return updatedUser.get().getId();

        } catch (InvalidUserException | UserNotFoundException ex) {
            log.warn("Dữ liệu user không hợp lệ hoặc user không tồn tại: {}", ex.getMessage());
            throw ex;
        } catch (UserRepositoryException ex) {
            log.error("Lỗi database khi cập nhật user: {}", ex.getMessage(), ex);
            throw new UpdateUserException(
                    "Lỗi lưu user vào database: " + ex.getMessage(),
                    ex
            );
        } catch (Exception ex) {
            log.error("Lỗi không mong muốn khi cập nhật user", ex);
            throw new UpdateUserException(
                    "Lỗi không mong muốn: " + ex.getMessage(),
                    ex
            );
        }
    }

    private void validateCommand(UpdateUserCommand command) {
        if (command == null) {
            throw UpdateUserException.missingRequiredField("command");
        }

        if (command.getUserId() == null || command.getUserId() <= 0) {
            throw UpdateUserException.missingRequiredField("userId");
        }

        if (command.getFullName() != null && command.getFullName().trim().isEmpty()) {
            throw InvalidUserException.emptyFullName();
        }
    }

    @Override
    public boolean supports(Object command) {
        return command instanceof UpdateUserCommand;
    }
}
