package backend.backend.useCases.authentication;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.password.PasswordEncoder;

import backend.backend.application.common.interfaces.IUserRepository;
import backend.backend.application.useCases.Authentication.LoginUseCase;
import backend.backend.application.useCases.Authentication.common.AuthenticationResult;
import backend.backend.context.SpringContextTest;
import backend.backend.domain.entities.User;
import backend.backend.presentation.contracts.Authentication.LoginRequest;
import backend.backend.presentation.errors.authentication.PasswordDontMatchException;
import backend.backend.presentation.errors.authentication.UserNotFoundException;

@DisplayName("Login Use Case Testing")
@ExtendWith(MockitoExtension.class)
@Import(SpringContextTest.class)
@SpringBootTest
public class LoginUseCaseTest {
    
    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private LoginUseCase sut;

    @BeforeEach()
    public void createUser() {
        this.userRepository.save(
            new User(
                "diogo@gmail.com",
                passwordEncoder.encode("password")
            )
        );
    }

    @AfterEach()
    public void cleanDb() {
        this.userRepository.deleteAll();
    }

    private AuthenticationResult makeSut(String email, String password) {
        return sut.handle(new LoginRequest(
            email, 
            password
        ));
    }

    @DisplayName("It should not allow login an user that does not exist")
    @Test
    public void testLogin() {

        assertThrows(
            UserNotFoundException.class, 
            () -> makeSut(
                "ricardo@gmail.com", 
                "password"
            )    
        );

    }

    @DisplayName("It should not allow login if password do not match")
    @Test
    public void testPasswordMatch() {
        assertThrows(
            PasswordDontMatchException.class, 
            () -> makeSut(
                "diogo@gmail.com", 
                ""
            )    
        );
    }

    @DisplayName("It should return tokens when authenticated")
    @Test
    public void testTokens() {
        assertInstanceOf(
            AuthenticationResult.class,
            makeSut(
                "diogo@gmail.com",
                "password"
            )    
        );
    }

}
