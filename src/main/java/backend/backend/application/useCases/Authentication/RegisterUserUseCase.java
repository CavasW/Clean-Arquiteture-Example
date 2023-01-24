package backend.backend.application.useCases.Authentication;

import org.springframework.stereotype.Service;

import backend.backend.application.common.interfaces.IMailSender;
import backend.backend.application.common.interfaces.IUserRepository;
import backend.backend.application.useCases.Authentication.common.AuthenticationResult;
import backend.backend.domain.entities.User;
import backend.backend.presentation.contracts.Authentication.RegisterRequest;

@Service
public class RegisterUserUseCase {
    
    private final IUserRepository userRepository;
    private final IMailSender mailSender;

    public RegisterUserUseCase(IUserRepository userRepository, IMailSender mailSender) {
        this.userRepository = userRepository;
        this.mailSender = mailSender;
    }

    public AuthenticationResult handle(RegisterRequest request) {

        // Validate if there are more users with that email!
        var user = this.userRepository.findByEmail(request.getEmail());

        if (user.isPresent()) {
            throw new RuntimeException("This user is already registered!");
        }

        // Perssiste user
        this.userRepository.save(new User(request.getEmail(), request.getPassword()));

        // Send Email
        mailSender.sendEmail(
            request.getEmail(),
            "Welcome to Reiport!", 
            null
        );

        return new AuthenticationResult(
            request.getEmail(),
            request.getEmail()
        );
        
    }

}
