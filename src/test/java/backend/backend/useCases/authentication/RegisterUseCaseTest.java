package backend.backend.useCases.authentication;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import backend.backend.application.common.interfaces.IUserRepository;
import backend.backend.application.useCases.Authentication.RegisterUserUseCase;
import backend.backend.application.useCases.Authentication.common.AuthenticationResult;
import backend.backend.context.SpringContextTest;
import backend.backend.domain.entities.User;
import backend.backend.presentation.contracts.Authentication.RegisterRequest;

@DisplayName("Register Use Case Testing")
@ExtendWith(MockitoExtension.class)
@Import(SpringContextTest.class)
@SpringBootTest
public class RegisterUseCaseTest {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private RegisterUserUseCase sut;

    private User sutUser = new User(
        "diogo@gmail.com", 
        "password"
    );

    private AuthenticationResult makeSut() {
        return sut.handle(new RegisterRequest(
                sutUser.getEmail(),
                sutUser.getPassword()
        ));
    }

    @AfterEach()
    public void tearDown() {
        this.userRepository.deleteAll();
    }

    @Test
    @DisplayName("It should not be able to register to users with the same email address")
    public void testEqualUsers() {

        this.userRepository.save(
            new User(
                "diogo@gmail.com", 
                "password"
            )
        );

        assertThrows(
            RuntimeException.class, 
            () -> makeSut()
        );

    }

    @Test
    @DisplayName("It should return tokens when authenticated")
    public void testTokens() {
        assertInstanceOf(
            AuthenticationResult.class, 
            makeSut()
        );
    }

}
