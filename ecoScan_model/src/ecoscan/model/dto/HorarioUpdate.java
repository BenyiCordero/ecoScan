package ecoscan.model.dto;

/**
 *
 * @author emont
 */
public record HorarioUpdate(
        Integer idHorario, 
        String diaSemana,
        String horaApertura, 
        String horaCierre) {}
