package backend.backend.presentation.errors.authentication;

import org.springframework.http.HttpStatus;

public class UserAlreadyRegisteredException extends BaseException {

    public UserAlreadyRegisteredException() {
        super(
            "This user is already registered!",
            HttpStatus.BAD_REQUEST
        );
    }  
}
