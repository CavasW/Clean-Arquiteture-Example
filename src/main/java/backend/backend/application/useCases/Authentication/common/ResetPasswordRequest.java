package backend.backend.application.useCases.Authentication.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ResetPasswordRequest {
    
    private String password;
    private String confirmPassword;
    private String token;

}
