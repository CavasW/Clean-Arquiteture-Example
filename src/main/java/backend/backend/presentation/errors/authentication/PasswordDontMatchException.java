package backend.backend.presentation.errors.authentication;

import org.springframework.http.HttpStatus;

public class PasswordDontMatchException extends BaseException { 

    public PasswordDontMatchException() {
        super(
            "The Email or password are wrong!",
            HttpStatus.BAD_REQUEST
        );
    }

}