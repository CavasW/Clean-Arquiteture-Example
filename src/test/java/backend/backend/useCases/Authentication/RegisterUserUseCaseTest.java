package backend.backend.useCases.Authentication;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import backend.backend.application.common.interfaces.IMailSender;
import backend.backend.application.common.interfaces.IUserRepository;
import backend.backend.application.useCases.Authentication.RegisterUserUseCase;
import backend.backend.domain.entities.User;
import backend.backend.infrastructure.presistence.InMemoryUserRepository;
import backend.backend.infrastructure.providers.JavaMailProvider;
import backend.backend.presentation.contracts.Authentication.RegisterRequest;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class RegisterUserUseCaseTest {
    
    @Spy 
    private IUserRepository userRepository = new InMemoryUserRepository();

    @Spy 
    private IMailSender mailSender = new JavaMailProvider();

    @InjectMocks
    private RegisterUserUseCase registerUserUseCase;

    @Test
    @DisplayName("It Should register an user")
    void registerUser() {

        var tokens = registerUserUseCase.handle(new RegisterRequest(
            "diogo@gmail.com", 
            "password"
        ));

        assertThat(tokens).isNotNull();

    }

    @Test
    @DisplayName("It shouldn't be able to register the same user")
    void cannotRegisterTheSameUser() {

        userRepository.save(new User(
            "diogo@gmail.com", 
            "password"
        ));

        assertThrows(
            RuntimeException.class, 
            () -> registerUserUseCase.handle(new RegisterRequest(
            "diogo@gmail.com", 
            "password"
        )));

    }

}
