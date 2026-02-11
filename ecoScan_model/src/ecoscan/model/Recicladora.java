package ecoscan.model;

/**
 *
 * @author Benyi Uriel
 */
public class Recicladora {
    
    private Integer idRecicladora;
    private String nombreRecicladora;
    private String direccion;
    private Double latitud;
    private Double longitud;

    public Recicladora() {
    }

    public Recicladora(String nombreRecicladora, String direccion, Double latitud, Double longitud) {
        this.nombreRecicladora = nombreRecicladora;
        this.direccion = direccion;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public Integer getIdRecicladora() {
        return idRecicladora;
    }

    public void setIdRecicladora(Integer idRecicladora) {
        this.idRecicladora = idRecicladora;
    }

    public String getNombreRecicladora() {
        return nombreRecicladora;
    }

    public void setNombreRecicladora(String nombreRecicladora) {
        this.nombreRecicladora = nombreRecicladora;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }
    
}
