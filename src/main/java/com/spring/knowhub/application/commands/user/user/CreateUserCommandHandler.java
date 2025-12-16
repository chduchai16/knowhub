package com.spring.knowhub.application.commands.user.user;

import com.spring.knowhub.application.buses.CommandHandler;
import com.spring.knowhub.application.exceptions.user.user.CreateUserCommandException;
import com.spring.knowhub.domain.enums.UserStatus;
import com.spring.knowhub.domain.exceptions.user.user.DuplicateUserException;
import com.spring.knowhub.domain.exceptions.user.user.InvalidUserException;
import com.spring.knowhub.domain.models.user.User;
import com.spring.knowhub.domain.repositories.user.UserRepository;
import com.spring.knowhub.infrastructure.exceptions.user.UserRepositoryException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class CreateUserCommandHandler implements CommandHandler<CreateUserCommand, Long> {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Long handle(CreateUserCommand command) {
        log.info("Bắt đầu xử lý CreateUserCommand cho username: {}", command.getUsername());

        try {
            validateCommand(command);
            
            // check trùng username
            Optional<User> existingUsername = userRepository.findByUsername(command.getUsername());
            if (existingUsername.isPresent()) {
                log.warn("Username '{}' đã tồn tại", command.getUsername());
                throw DuplicateUserException.usernameAlreadyExists(command.getUsername());
            }
            
            // check trùng email
            Optional<User> existingEmail = userRepository.findByEmail(command.getEmail());
            if (existingEmail.isPresent()) {
                log.warn("Email '{}' đã tồn tại", command.getEmail());
                throw DuplicateUserException.emailAlreadyExists(command.getEmail());
            }

            String hashedPassword = passwordEncoder.encode(command.getPassword());

            User user = new User(
                    null,
                    command.getUsername(),
                    command.getEmail(),
                    hashedPassword,
                    command.getFullName(),
                    command.getBio(),
                    command.getAvatarUrl(),
                    UserStatus.ACTIVE,
                    null
            );

            Optional<User> savedUser = userRepository.save(user);

            log.info("Tạo user thành công với ID: {}", savedUser.get().getId());
            return savedUser.get().getId();
        } catch (InvalidUserException ex) {
            log.warn("Dữ liệu user không hợp lệ: {}", ex.getMessage());
            throw new CreateUserCommandException(
                    "Dữ liệu user không hợp lệ: " + ex.getMessage(),
                    ex
            );
        } catch (DuplicateUserException ex) {
            log.warn("User bị trùng lặp: {}", ex.getMessage());
            throw new CreateUserCommandException(
                    "User đã tồn tại: " + ex.getMessage(),
                    ex
            );
        } catch (UserRepositoryException ex) {
            log.error("Lỗi database khi tạo user: {}", ex.getMessage(), ex);
            throw new CreateUserCommandException(
                    "Lỗi lưu user vào database: " + ex.getMessage(),
                    ex
            );
        } catch (Exception ex) {
            log.error("Lỗi không mong muốn khi tạo user", ex);
            throw new CreateUserCommandException(
                    "Lỗi không mong muốn: " + ex.getMessage(),
                    ex
            );
        }
    }

    private void validateCommand(CreateUserCommand command) {
        if (command == null) {
            throw CreateUserCommandException.missingRequiredField("command");
        }

        //username
        if (command.getUsername() == null || command.getUsername().trim().isEmpty()) {
            throw CreateUserCommandException.missingRequiredField("username");
        }
        
        if (!isValidUsername(command.getUsername())) {
            throw new CreateUserCommandException(
                    "Dữ liệu user không hợp lệ: " + 
                    InvalidUserException.invalidUsername(command.getUsername()).getMessage(),
                    InvalidUserException.invalidUsername(command.getUsername())
            );
        }

        // email
        if (command.getEmail() == null || command.getEmail().trim().isEmpty()) {
            throw CreateUserCommandException.missingRequiredField("email");
        }
        
        if (!isValidEmail(command.getEmail())) {
            throw new CreateUserCommandException(
                    "Dữ liệu user không hợp lệ: " + 
                    InvalidUserException.invalidEmail(command.getEmail()).getMessage(),
                    InvalidUserException.invalidEmail(command.getEmail())
            );
        }

        // password
        if (command.getPassword() == null || command.getPassword().trim().isEmpty()) {
            throw CreateUserCommandException.missingRequiredField("password");
        }
        
        if (!isValidPassword(command.getPassword())) {
            throw new CreateUserCommandException(
                    "Dữ liệu user không hợp lệ: " + 
                    InvalidUserException.weakPassword().getMessage(),
                    InvalidUserException.weakPassword()
            );
        }

        // fullName
        if (command.getFullName() == null || command.getFullName().trim().isEmpty()) {
            throw new CreateUserCommandException(
                    "Dữ liệu user không hợp lệ: " + 
                    InvalidUserException.emptyFullName().getMessage(),
                    InvalidUserException.emptyFullName()
            );
        }
    }

    // username : chỉ chứa chữ cái, số, dấu gạch dưới, từ 3-20 ký tự
    private boolean isValidUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            return false;
        }
        String trimmed = username.trim();
        return trimmed.length() >= 3 && trimmed.length() <= 20 && 
               trimmed.matches("^[a-zA-Z0-9_]+$");
    }

    // email
    private boolean isValidEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return false;
        }
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        return email.trim().matches(emailRegex);
    }

    // password: ít nhất 8 ký tự, chứa chữ hoa, chữ thường và số
    private boolean isValidPassword(String password) {
        if (password == null || password.length() < 8) {
            return false;
        }
        
        boolean hasUpperCase = password.matches(".*[A-Z].*");
        boolean hasLowerCase = password.matches(".*[a-z].*");
        boolean hasDigit = password.matches(".*[0-9].*");
        
        return hasUpperCase && hasLowerCase && hasDigit;
    }

    @Override
    public boolean supports(Object command) {
        return command instanceof CreateUserCommand;
    }
}
