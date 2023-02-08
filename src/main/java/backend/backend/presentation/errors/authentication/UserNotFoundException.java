package backend.backend.presentation.errors.authentication;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends BaseException {

    public UserNotFoundException() {
        super(
            "There isn't any user with that email", 
            HttpStatus.BAD_REQUEST
        );
    }
}
