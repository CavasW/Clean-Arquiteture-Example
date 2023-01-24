package backend.backend.presentation.contracts.Authentication;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RegisterRequest {
    
    @Email()
    @NotBlank()
    private String email;

    @Size(min = 5, max = 10)
    @NotBlank()
    private String password;

}
