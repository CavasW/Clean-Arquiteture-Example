package backend.backend.useCases.authentication;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import backend.backend.application.useCases.Authentication.ForgotPasswordUseCase;
import backend.backend.context.SpringContextTest;
import backend.backend.presentation.contracts.Authentication.ForgotPasswordRequest;
import backend.backend.presentation.errors.authentication.UserNotFoundException;

@DisplayName("ForgotPassword Use Case Testing")
@ExtendWith(MockitoExtension.class)
@Import(SpringContextTest.class)
@SpringBootTest
public class ForgotPasswordUseCaseTest {
 
    @Autowired
    private ForgotPasswordUseCase sut;

    private void makeSut(String email) {
        sut.handle(
            new ForgotPasswordRequest(
                email
            )
        );
    }

    @DisplayName("It should not allow send an forgotpassword request if user email don't exist")
    @Test
    public void testLogin() {

        assertThrows(
            UserNotFoundException.class, 
            () -> makeSut("diogo@gmail.com")
        );

    }

}
