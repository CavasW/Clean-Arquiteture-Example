package backend.backend.application.useCases.Authentication;

import org.springframework.stereotype.Service;

import backend.backend.application.common.interfaces.IUserRepository;
import backend.backend.application.useCases.Authentication.common.AuthenticationResult;
import backend.backend.presentation.contracts.Authentication.LoginRequest;

@Service
public class LoginUseCase {
    
    private final IUserRepository userRepository;

    public LoginUseCase(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public AuthenticationResult handle(LoginRequest request) {

        // verify if user is registered
        var userFound = this.userRepository.findByEmail(request.getEmail());

        if (userFound.isEmpty()) {
            throw new RuntimeException("There isn't any user with that email");
        }

        // verify passwords

        // Generate Tokens

        return new AuthenticationResult(
            null, 
            null
        );

    }

}
