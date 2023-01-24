package backend.backend.application.common.interfaces;

import com.auth0.jwt.interfaces.DecodedJWT;

public interface IJwtGenerator {

    String generateToken(String id, String email);
    DecodedJWT decodeToken(String token);
    boolean validateToken(String token);
    
}