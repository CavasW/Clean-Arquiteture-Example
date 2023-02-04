package backend.backend.presentation.errors.authentication;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Passwords don't match")
public class PasswordsNotEqualException extends RuntimeException {  }