package backend.backend.presentation.errors.authentication;

import org.springframework.http.HttpStatus;

public class UserDontOwnTokenException extends BaseException {

    public UserDontOwnTokenException() {
        super(
            "User does not own this token",
            HttpStatus.BAD_REQUEST
        );
    }
}
