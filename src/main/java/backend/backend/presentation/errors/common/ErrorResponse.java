package backend.backend.presentation.errors.common;

import java.util.Date;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private Date timestamp;

    private int code;

    private String status;

    private String message;

    private String path;

    public ErrorResponse() {
        timestamp = new Date();
    }

    public ErrorResponse(
            HttpStatus httpStatus,
            String message,
            String path
    ) {
        this();
    
        this.code = httpStatus.value();
        this.status = httpStatus.name();
        this.message = message;
        this.path = path;
    }

}
