package ecoscan.model.dto;

/**
 *
 * @author Benyi Uriel
 */
public record RegisterRequest(
        String nombre,
        String primerApellido,
        String segundoApellido,
        String email,
        String password
    ) {}
