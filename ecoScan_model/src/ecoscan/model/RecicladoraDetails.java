package ecoscan.model;

/**
 *
 * @author Benyi Uriel
 */
public class RecicladoraDetails {
    
    private Integer idRecicladoraDetails;
    private Recicladora recicladora;
    private Material material;

    public RecicladoraDetails() {
    }

    public RecicladoraDetails(Integer idRecicladoraDetails, Recicladora recicladora, Material material) {
        this.idRecicladoraDetails = idRecicladoraDetails;
        this.recicladora = recicladora;
        this.material = material;
    }

    public Integer getIdRecicladoraDetails() {
        return idRecicladoraDetails;
    }

    public void setIdRecicladoraDetails(Integer idRecicladoraDetails) {
        this.idRecicladoraDetails = idRecicladoraDetails;
    }

    public Recicladora getRecicladora() {
        return recicladora;
    }

    public void setRecicladora(Recicladora recicladora) {
        this.recicladora = recicladora;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }
    
}
