package backend.backend.presentation.controllers;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.backend.application.useCases.Authentication.LoginUseCase;
import backend.backend.application.useCases.Authentication.RegisterUserUseCase;
import backend.backend.application.useCases.Authentication.common.AuthenticationResult;
import backend.backend.presentation.contracts.Authentication.LoginRequest;
import backend.backend.presentation.contracts.Authentication.RegisterRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequestMapping("/auth")
@RestController
@RequiredArgsConstructor
public class AuthenticationController {
    
    private final RegisterUserUseCase registerUseCase;
    private final LoginUseCase loginUseCase;

    @PostMapping("/register")
    private ResponseEntity<AuthenticationResult> register(@Valid @RequestBody RegisterRequest request) {

        var tokens = this.registerUseCase.handle(new RegisterRequest(
            request.getEmail(), 
            request.getPassword()
        ));

        return new ResponseEntity<AuthenticationResult>(
            tokens, 
            HttpStatusCode.valueOf(201)
        );

    }

    @PostMapping("/login")
    private ResponseEntity<AuthenticationResult> login(@Valid @RequestBody LoginRequest request) {

        var tokens = this.loginUseCase.handle(new LoginRequest(
            request.getEmail(), 
            request.getPassword()
        ));

        return new ResponseEntity<AuthenticationResult>(
            tokens, 
            HttpStatusCode.valueOf(200)
        );

    }
    
}
