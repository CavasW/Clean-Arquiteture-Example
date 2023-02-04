package backend.backend.application.useCases.Authentication;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import backend.backend.application.common.interfaces.repositories.ITokenRepository;
import backend.backend.application.common.interfaces.repositories.IUserRepository;
import backend.backend.application.useCases.Authentication.common.ResetPasswordRequest;
import backend.backend.domain.entities.Token;
import backend.backend.domain.entities.User;
import backend.backend.presentation.errors.authentication.PasswordsNotEqualException;
import backend.backend.presentation.errors.authentication.TokenNotFoundException;
import backend.backend.presentation.errors.authentication.UserDontOwnTokenException;



@Service
public class ResetPasswordUseCase {
    
    IUserRepository userRepository;
    ITokenRepository tokenRepository;
    PasswordEncoder passwordEncoder;

    public ResetPasswordUseCase(
        IUserRepository userRepository,
        ITokenRepository tokenRepository,
        PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
        this.passwordEncoder = passwordEncoder;
    
    public void handle(ResetPasswordRequest request) {

        User currentUser;

        // get token
        Optional<Token> tokenFound = tokenRepository.findByValue(request.getToken());

        if (tokenFound.isEmpty()) {
            throw new TokenNotFoundException();
        }

        Optional<User> userFound = userRepository.findById(tokenFound.get().getUserId());

        if (userFound.isEmpty()) {
            tokenRepository.delete(tokenFound.get());
            throw new UserDontOwnTokenException();
        }

        currentUser = userFound.get();

        if (!request.getPassword().equals(request.getConfirmPassword())) {
            throw new PasswordsNotEqualException();
        }

        currentUser.setPassword(passwordEncoder.encode(request.getPassword()));

        userRepository.save(currentUser);

        tokenRepository.delete(tokenFound.get());

    }

}
