package backend.backend.application.useCases.Authentication;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import backend.backend.application.common.interfaces.IJwtGenerator;
import backend.backend.application.common.interfaces.IMailSender;
import backend.backend.application.common.interfaces.IUserRepository;
import backend.backend.domain.entities.User;
import backend.backend.presentation.contracts.Authentication.ForgotPasswordRequest;
import backend.backend.presentation.errors.authentication.UserNotFoundException;

@Service
public class ForgotPasswordUseCase {
    
    private final IUserRepository userRepository;
    private final IJwtGenerator jwtGenerator;
    private final IMailSender mailSender;

    public ForgotPasswordUseCase(
        IUserRepository userRepository,
        IJwtGenerator jwtGenerator,
        IMailSender mailSender
    ) {
        this.userRepository = userRepository;
        this.jwtGenerator = jwtGenerator;
        this.mailSender = mailSender;
    }

    public void handle(ForgotPasswordRequest request) {

        Optional<User> user = this.userRepository.findByEmail(request.getEmail());

        if (user.isEmpty()) {
            throw new UserNotFoundException();
        }

        String forgotToken = jwtGenerator.generateSimpleToken();

        // Save in Redis

        Map<String, Object> options = new HashMap<>();
        
        options.put("name", user.get().getEmail());
        options.put("forgotLink",
            "localhost:8080/changepassword?token=" + forgotToken
        );

        mailSender.sendEmail(
            request.getEmail(), 
            "forgotpassword", 
            options
        );

    }
}
