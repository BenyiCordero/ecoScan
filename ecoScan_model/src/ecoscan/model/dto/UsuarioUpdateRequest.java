package ecoscan.model.dto;

/**
 *
 * @author Benyi Uriel
 */
public record UsuarioUpdateRequest(
        Integer idUsuario,
        String nombre,
        String primerApellido,
        String segundoApellido
    ) {}
