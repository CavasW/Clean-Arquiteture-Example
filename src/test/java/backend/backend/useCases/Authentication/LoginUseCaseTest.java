package backend.backend.useCases.Authentication;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import backend.backend.application.common.interfaces.IUserRepository;
import backend.backend.application.useCases.Authentication.LoginUseCase;
import backend.backend.application.useCases.Authentication.common.AuthenticationResult;
import backend.backend.domain.entities.User;
import backend.backend.infrastructure.presistence.InMemoryUserRepository;
import backend.backend.presentation.contracts.Authentication.LoginRequest;

@ExtendWith(MockitoExtension.class)
public class LoginUseCaseTest {
    
    @Spy
    private IUserRepository userRepository = new InMemoryUserRepository();

    @InjectMocks
    private LoginUseCase sut;

    @Test
    @DisplayName("It should login an user")
    void login() {

        userRepository.save(new User(
            "diogo@gmail.com", 
            "password"
        ));

        var tokens = sut.handle(new LoginRequest(
            "diogo@gmail.com",
            "password"
        ));

        assertThat(tokens)
            .isExactlyInstanceOf(AuthenticationResult.class);

    }

    @Test
    @DisplayName("It should not login an user with a non valid email")
    void tryLogin() {

        assertThrows(
            RuntimeException.class,
            () -> sut.handle(new LoginRequest(
                "no-valid@gmail.com",
                "password"
            ))
        );

    }

}
