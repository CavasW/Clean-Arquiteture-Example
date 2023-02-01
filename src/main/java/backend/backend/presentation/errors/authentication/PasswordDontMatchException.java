package backend.backend.presentation.errors.authentication;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "The Email or password are wrong!")
public class PasswordDontMatchException extends RuntimeException {  }