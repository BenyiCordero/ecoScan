package ecoscan.model.dto;

/**
 *
 * @author emont
 */
public record RecicladoraUpdate(
        Integer idRecicladora, 
        String nombreRecicladora,
        String direccion, 
        Double latitud, 
        Double longitud) {}
