package ecoscan.model;

/**
 *
 * @author Benyi Uriel
 */
public class Material {
    
    private Integer idMaterial;
    private String nombreMaterial;

    public Material() {
    }

    public Material(String nombreMaterial) {
        this.nombreMaterial = nombreMaterial;
    }

    public Integer getIdMaterial() {
        return idMaterial;
    }

    public void setIdMaterial(Integer idMaterial) {
        this.idMaterial = idMaterial;
    }

    public String getNombreMaterial() {
        return nombreMaterial;
    }

    public void setNombreMaterial(String nombreMaterial) {
        this.nombreMaterial = nombreMaterial;
    }
    
}
