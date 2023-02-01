package backend.backend.presentation.errors.authentication;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "There isn't any user with that email")
public class UserNotFoundException extends RuntimeException {  }
