package ecoscan.model.dto;

/**
 *
 * @author emont
 */
// para agregar material a recicladora
public record MaterialRecicladoraRequest(
        Integer idRecicladora, 
        Integer idMaterial) {}
