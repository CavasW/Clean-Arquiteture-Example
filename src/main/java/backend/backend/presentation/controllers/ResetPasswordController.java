package backend.backend.presentation.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import backend.backend.application.useCases.Authentication.ResetPasswordUseCase;
import backend.backend.application.useCases.Authentication.common.ResetPasswordRequest;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ResetPasswordController {

    private final ResetPasswordUseCase resetPasswordUseCase;

    @PostMapping("/resetpassword")
    private ResponseEntity<?> resetPassword(@RequestParam String token, @RequestBody backend.backend.presentation.contracts.Authentication.ResetPasswordRequest request) {

        this.resetPasswordUseCase.handle(
            new ResetPasswordRequest(
                request.getPassword(), 
                request.getConfirmPassword(), 
                token
            )
        );

        return ResponseEntity.ok().build();

    }

}
