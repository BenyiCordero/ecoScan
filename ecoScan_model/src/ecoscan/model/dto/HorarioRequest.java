package ecoscan.model.dto;

/**
 *
 * @author emont
 */
public record HorarioRequest(
        String diaSemana, 
        String horaApertura, 
        String horaCierre) {}
