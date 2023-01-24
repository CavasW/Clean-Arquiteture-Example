package backend.backend.infrastructure.presistence;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import backend.backend.application.common.interfaces.IUserRepository;
import backend.backend.domain.entities.User;

public class InMemoryUserRepository implements IUserRepository {

    private Collection<User> users = new ArrayList<>();

    @Override
    public Optional<User> findByEmail(String email) {
        return users
                .stream()
                    .filter(user -> user.getEmail().equals(email)).findAny();
    }

    @Override
    public void save(User user) {
        this.users.add(user);
    }

    @Override
    public Optional<Collection<User>> getAll() {
        return Optional.of(users);
    }
    
}
