package ecoscan.model.dto;

/**
 *
 * @author emont
 */
public record RecicladoraRequest(
        String nombreRecicladora, 
        String direccion,
        Double latitud, 
        Double longitud) {}
