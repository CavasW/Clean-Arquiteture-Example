package backend.backend.application.common.interfaces.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import backend.backend.domain.entities.User;

@Repository
public interface IUserRepository extends JpaRepository<User, String> {
    
    Optional<User> findByEmail(String email);
    Optional<User> findById(UUID id);

}
