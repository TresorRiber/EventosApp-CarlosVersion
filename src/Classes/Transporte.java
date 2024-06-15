package Classes;

public class Transporte {
    private int idTransporte;
    private int servicioNocturno;
    private float tarifa;
    private String descripcion;
    private String horarios;
    private String tipo;

    public Transporte(int idTransporte, int servicioNocturno, float tarifa, String descripcion, String horarios, String tipo){
        this.idTransporte=idTransporte;
        this.servicioNocturno=servicioNocturno;
        this.tarifa=tarifa;
        this.descripcion=descripcion;
        this.horarios=horarios;
        this.tipo=tipo;
    }

    // Getters and setters
    public int getIdTransporte() {
        return idTransporte;
    }

    public void setIdTransporte(int idTransporte) {
        this.idTransporte = idTransporte;
    }

    public int getServicioNocturno() {
        return servicioNocturno;
    }

    public void setServicioNocturno(int servicioNocturno) {
        this.servicioNocturno = servicioNocturno;
    }

    public float getTarifa() {
        return tarifa;
    }

    public void setTarifa(float tarifa) {
        this.tarifa = tarifa;
    }

    @Override
    public String toString() {
        return "Transporte{" +
                "idTransporte=" + idTransporte +
                ", servicioNocturno=" + servicioNocturno +
                ", tarifa=" + tarifa +
                ", descripcion='" + descripcion + '\'' +
                ", horarios='" + horarios + '\'' +
                ", tipo='" + tipo + '\'' +
                '}';
    }
}
