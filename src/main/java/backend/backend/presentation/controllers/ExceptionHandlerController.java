package backend.backend.presentation.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import backend.backend.presentation.errors.authentication.BaseException;
import backend.backend.presentation.errors.common.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionHandlerController {
    
    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ErrorResponse> handleBadRequest(BaseException e, HttpServletRequest request) throws IOException {

        return new ResponseEntity<>(
            new ErrorResponse(
              e.getStatus(), 
              e.getMessage(),
              request.getRequestURL().toString()
            ),
            e.getStatus()
        );

    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleInternalServerError(Exception e, HttpServletRequest request) throws IOException {

        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR; // 500

        // converting the stack trace to String
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        e.printStackTrace(printWriter);

        return new ResponseEntity<>(
            new ErrorResponse(
              status, 
              e.getMessage(),
              request.getRequestURL().toString()
            ),
            status
        );

    }

}
