package com.spring.knowhub.application.validators.user.user;

import com.spring.knowhub.application.commands.user.user.CreateUserCommand;
import com.spring.knowhub.application.exceptions.user.user.CreateUserException;
import com.spring.knowhub.domain.exceptions.user.user.InvalidUserException;

public class CreateUserValidator {

    public static void validate(CreateUserCommand command) {
        if (command == null) {
            throw CreateUserException.missingRequiredField("command");
        }

        validateUsername(command.getUsername());
        validateEmail(command.getEmail());
        validatePassword(command.getPassword());
        validateFullName(command.getFullName());
    }

    private static void validateUsername(String username) {
        if (isBlank(username)) {
            throw CreateUserException.missingRequiredField("username");
        }

        String trimmed = username.trim();

        if (trimmed.length() < 3 || trimmed.length() > 20) {
            throw InvalidUserException.invalidUsername(username);
        }

        if (!trimmed.matches("^[a-zA-Z0-9_]+$")) {
            throw InvalidUserException.invalidUsername(username);
        }
    }

    private static void validateEmail(String email) {
        if (isBlank(email)) {
            throw CreateUserException.missingRequiredField("email");
        }

        String trimmed = email.trim();
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

        if (!trimmed.matches(emailRegex)) {
            throw InvalidUserException.invalidEmail(email);
        }
    }

    private static void validatePassword(String password) {
        if (isBlank(password)) {
            throw CreateUserException.missingRequiredField("password");
        }

        if (password.length() < 8) {
            throw InvalidUserException.weakPassword();
        }

        boolean hasUpper = password.matches(".*[A-Z].*");
        boolean hasLower = password.matches(".*[a-z].*");
        boolean hasDigit = password.matches(".*\\d.*");

        if (!(hasUpper && hasLower && hasDigit)) {
            throw InvalidUserException.weakPassword();
        }
    }

    private static void validateFullName(String fullName) {
        if (isBlank(fullName)) {
            throw InvalidUserException.emptyFullName();
        }
    }

    private static boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}
