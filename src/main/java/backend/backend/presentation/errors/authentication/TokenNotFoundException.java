package backend.backend.presentation.errors.authentication;

import org.springframework.http.HttpStatus;

public class TokenNotFoundException extends BaseException {

    public TokenNotFoundException() {
        super(
            "Token is invalid",
            HttpStatus.BAD_REQUEST
        );
    }  
}
