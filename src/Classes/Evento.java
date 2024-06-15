package Classes;

public class Evento {
    private int idEvento;
    private String accesibilidad;
    private String estado;
    private String fechaFinalizacion;
    private String fechaInicio;
    private String nombre;
    private String tipoEvento;
    private Organizador organizador;
    private Ubicacion ubicacion;

    public Evento(int idEvento, String accesibilidad, String estado, String fechaFinalizacion, String fechaInicio, String nombre, String tipoEvento, Organizador organizador, Ubicacion ubicacion){
        this.idEvento=idEvento;
        this.accesibilidad=accesibilidad;
        this.estado=estado;
        this.fechaFinalizacion=fechaFinalizacion;
        this.fechaInicio=fechaInicio;
        this.nombre=nombre;
        this.tipoEvento=tipoEvento;
        this.organizador=organizador;
        this.ubicacion=ubicacion;
    }

    // Getters and setters
    public int getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }

    public String getAccesibilidad() {
        return accesibilidad;
    }

    public void setAccesibilidad(String accesibilidad) {
        this.accesibilidad = accesibilidad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFechaFinal() {
        return fechaFinalizacion;
    }

    public void setFechaFinal(String fechaFinal) {
        this.fechaFinalizacion = fechaFinal;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipoEvento() {
        return tipoEvento;
    }

    public void setTipoEvento(String tipoEvento) {
        this.tipoEvento = tipoEvento;
    }

    public Organizador getOrganizador() {
        return organizador;
    }

    public void setOrganizador(Organizador organizador) {
        this.organizador = organizador;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    @Override
    public String toString() {
        return "Evento{" +
                "idEvento=" + idEvento +
                ", accesibilidad='" + accesibilidad + '\'' +
                ", estado='" + estado + '\'' +
                ", fechaFinal='" + fechaFinalizacion + '\'' +
                ", fechaInicio='" + fechaInicio + '\'' +
                ", nombre='" + nombre + '\'' +
                ", tipoEvento='" + tipoEvento + '\'' +
                ", organizador=" + organizador +'\'' +
                ", ubicacion=" + ubicacion +
                '}';
    }
}
