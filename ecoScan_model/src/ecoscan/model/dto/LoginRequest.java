package ecoscan.model.dto;

/**
 *
 * @author Benyi Uriel
 */
public record LoginRequest(
        String email,
        String password
    ) {}
