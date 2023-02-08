package backend.backend.presentation.errors.authentication;

import org.springframework.http.HttpStatus;

public class PasswordsNotEqualException extends BaseException {

    public PasswordsNotEqualException() {
        super(
            "Passwords don't match", 
            HttpStatus.BAD_REQUEST
        );
    }
}