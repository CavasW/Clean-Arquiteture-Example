package backend.backend.application.useCases.Authentication;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import backend.backend.application.common.interfaces.IJwtGenerator;
import backend.backend.application.common.interfaces.repositories.IUserRepository;
import backend.backend.application.useCases.Authentication.common.AuthenticationResult;
import backend.backend.presentation.contracts.Authentication.LoginRequest;
import backend.backend.presentation.errors.authentication.UserNotFoundException;
import backend.backend.presentation.errors.authentication.PasswordDontMatchException;

@Service
public class LoginUseCase {
    
    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final IJwtGenerator jwtGenerator;
    private final AuthenticationManager authenticationManager;

    public LoginUseCase(
        IUserRepository userRepository, 
        PasswordEncoder passwordEncoder,
        IJwtGenerator jwtGenerator,
        AuthenticationManager authenticationManager
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtGenerator = jwtGenerator;
        this.authenticationManager = authenticationManager;
    }

    public AuthenticationResult handle(LoginRequest request) {

        // verify if user is registered
        var userFound = this.userRepository.findByEmail(request.getEmail());

        if (userFound.isEmpty()) {
            throw new UserNotFoundException();
        }

        // verify passwords
        if (!passwordEncoder.matches(request.getPassword(), userFound.get().getPassword())) {
            throw new PasswordDontMatchException();
        }

        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(userFound.get(), request.getPassword())
        );

        // Generate TokensP
        String token = jwtGenerator.generateToken(userFound.get().getId().toString(), request.getEmail());
        String refreshToken = jwtGenerator.generateToken(userFound.get().getId().toString(), request.getEmail());

        return new AuthenticationResult(
            token, 
            refreshToken
        );

    }

}
