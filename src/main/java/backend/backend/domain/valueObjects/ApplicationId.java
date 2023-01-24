package backend.backend.domain.valueObjects;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;

public class ApplicationId implements IValueObject {
    
    private final UUID userUuid;

    public ApplicationId() {
        this.userUuid = validate();
    }

    @Override
    public String toString() {
        return userUuid.toString();
    }

    public static UUID validate() {

        UUID uuid = UUID.randomUUID();

        if (!isValid(uuid.toString())) {
            throw new IllegalArgumentException("Invalid UUID");
        }

        return uuid;

    }

    public static boolean isValid(@NotNull String id) {
        return true;
    }

}
