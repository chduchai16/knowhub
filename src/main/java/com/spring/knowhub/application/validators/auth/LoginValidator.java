package com.spring.knowhub.application.validators.auth;

import com.spring.knowhub.application.commands.auth.LoginCommand;
import com.spring.knowhub.application.exceptions.auth.InvalidLoginException;

public class LoginValidator {
    public static void validate(LoginCommand command){
        if(command == null) {
            throw InvalidLoginException.objectNull() ;
        } else {
            validateUsername(command.getUsername());
            validatePassword(command.getPassword());
            if(command.getRememberMe() == null) {
                command.setRememberMe(false);
            }
        }
    }

    private static void validateUsername(String username) {
        if (username == null || username.isEmpty()) {
            throw InvalidLoginException.missingRequiredFields("username");
        }
        if (username.contains(" ")) {
            throw InvalidLoginException.invalidFieldFormat("username", "Username không được chứa khoảng trắng.");
        }
        if (username.length() < 3 || username.length() > 20) {
            throw InvalidLoginException.invalidFieldFormat("username", "Độ dài username phải từ 3 đến 20 ký tự.");
        }
        if (!username.matches("^[a-zA-Z0-9_]+$")) {
            throw InvalidLoginException.invalidFieldFormat("username", "Username chỉ được chứa chữ cái, số và dấu gạch dưới.");
        }
    }

    private static void validatePassword(String password) {
        if (password == null || password.isEmpty()) {
            throw InvalidLoginException.missingRequiredFields("password");
        }

        if (password.length() < 8) {
            throw InvalidLoginException.invalidFieldFormat("password", "Mật khẩu phải có ít nhất 8 ký tự.");
        }

        boolean hasUpper = password.matches(".*[A-Z].*");
        boolean hasLower = password.matches(".*[a-z].*");
        boolean hasDigit = password.matches(".*\\d.*");

        if (!(hasUpper && hasLower && hasDigit)) {
            throw InvalidLoginException.invalidFieldFormat("password", "Mật khẩu phải bao gồm chữ hoa, chữ thường và số.");
        }
    }
}
