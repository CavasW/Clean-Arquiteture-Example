package backend.backend.infrastructure.providers.Authentication;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import backend.backend.application.common.interfaces.repositories.IUserRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AuthenticationService implements UserDetailsService {

    private final IUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        var userFound = this.userRepository.findByEmail(email);

        if (userFound.isEmpty()) {
            throw new UsernameNotFoundException("There is no User with that email!");
        }

        return userFound.get();

    }
    
}
