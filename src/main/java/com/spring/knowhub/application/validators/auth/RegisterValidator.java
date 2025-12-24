package com.spring.knowhub.application.validators.auth;
import com.spring.knowhub.application.commands.auth.RegisterCommand;
import com.spring.knowhub.application.exceptions.auth.InvalidRegisterException;

public class RegisterValidator {
    public static void validate (RegisterCommand command){
        if(command == null){
            throw InvalidRegisterException.objectNull();
        } else {
            validateUsername( command.getUsername() );
            validateEmail( command.getEmail() );
            validatePassword( command.getPassword() );
        }
    }

    private static void validateUsername(String username) {
        if (username == null || username.isEmpty()) {
            throw InvalidRegisterException.missingRequiredFields("username");
        }
        if (username.contains(" ")) {
            throw InvalidRegisterException.invalidFieldFormat("username", "Username không được chứa khoảng trắng.");
        }
        if (username.length() < 3 || username.length() > 20) {
            throw InvalidRegisterException.invalidFieldFormat("username", "Độ dài username phải từ 3 đến 20 ký tự.");
        }
        if (!username.matches("^[a-zA-Z0-9_]+$")) {
            throw InvalidRegisterException.invalidFieldFormat("username", "Username chỉ được chứa chữ cái, số và dấu gạch dưới.");
        }
    }

    private static void validatePassword(String password) {
        if (password == null || password.isEmpty()) {
            throw InvalidRegisterException.missingRequiredFields("password");
        }

        if (password.length() < 8) {
            throw InvalidRegisterException.invalidFieldFormat("password", "Mật khẩu phải có ít nhất 8 ký tự.");
        }

        boolean hasUpper = password.matches(".*[A-Z].*");
        boolean hasLower = password.matches(".*[a-z].*");
        boolean hasDigit = password.matches(".*\\d.*");

        if (!(hasUpper && hasLower && hasDigit)) {
            throw InvalidRegisterException.invalidFieldFormat("password", "Mật khẩu phải chứa ít nhất một chữ cái viết hoa, một chữ cái viết thường và một chữ số.");
        }
    }

    private static void validateEmail(String email) {
        if (email == null || email.isEmpty()) {
            throw InvalidRegisterException.missingRequiredFields("email");
        }

        String trimmed = email.trim();
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

        if (!trimmed.matches(emailRegex)) {
            throw InvalidRegisterException.invalidFieldFormat("email", "Định dạng email không hợp lệ.");
        }
    }

}
