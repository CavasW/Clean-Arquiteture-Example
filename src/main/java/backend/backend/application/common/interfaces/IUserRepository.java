package backend.backend.application.common.interfaces;

import java.util.Collection;
import java.util.Optional;

import backend.backend.domain.entities.User;

public interface IUserRepository {
    
    Optional<Collection<User>> getAll();
    Optional<User> findByEmail(String email);
    void save(User user);

}
