package ecoscan.model;

/**
 *
 * @author Benyi Uriel
 */
public record LoginRequest(
        String email,
        String password
    ) {}
