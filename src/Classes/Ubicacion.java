package Classes;

public class Ubicacion {
    private int idUbicacion;
    private int cp;
    private String direccion;
    private String municipio;

    public Ubicacion(int idUbicacion, int cp, String direccion, String municipio){
        this.idUbicacion=idUbicacion;
        this.cp=cp;
        this.direccion=direccion;
        this.municipio=municipio;
    }

    // Getters and setters
    public int getIdUbicacion() {
        return idUbicacion;
    }

    public void setIdUbicacion(int idUbicacion) {
        this.idUbicacion = idUbicacion;
    }

    public int getCp() {
        return cp;
    }

    public void setCp(int cp) {
        this.cp = cp;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    @Override
    public String toString() {
        return "Ubicacion{" +
                "idUbicacion=" + idUbicacion +
                ", cp=" + cp +
                ", direccion='" + direccion + '\'' +
                ", municipio='" + municipio + '\'' +
                '}';
    }
}
