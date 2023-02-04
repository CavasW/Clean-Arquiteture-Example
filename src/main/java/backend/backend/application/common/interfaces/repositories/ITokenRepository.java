package backend.backend.application.common.interfaces.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import backend.backend.domain.entities.Token;

public interface ITokenRepository extends CrudRepository<Token, UUID> {
    
    Optional<Token> findByValue(String value);

}
