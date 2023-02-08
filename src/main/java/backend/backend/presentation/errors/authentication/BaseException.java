package backend.backend.presentation.errors.authentication;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseException extends RuntimeException {
    
    private HttpStatus status;

    public BaseException(String message, HttpStatus status) {
        
        super(message);
        this.status = status;

    }

}
