package backend.backend.presentation.errors.authentication;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "User does not own this token")
public class UserDontOwnTokenException extends RuntimeException {  }
