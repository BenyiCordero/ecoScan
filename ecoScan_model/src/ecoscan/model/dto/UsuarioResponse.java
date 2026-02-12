package ecoscan.model.dto;

/**
 *
 * @author Benyi Uriel
 */
public record UsuarioResponse(
        String email,
        String nombre,
        String primerApellido,
        String segundoApellido,
        String rol
    ) {}
