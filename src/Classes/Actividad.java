package Classes;

public class Actividad {
    private int idActividad;
    private String nombre;
    private String descripcion;
    private String fechaInicio;
    private String fechaFinal;
    private Evento evento;

    public Actividad(int idActividad, String nombre, String descricion, String fechaInicio, String fechaFinal, Evento evento){
        this.idActividad=idActividad;
        this.nombre=nombre;
        this.descripcion=descripcion;
        this.fechaInicio=fechaInicio;
        this.fechaFinal=fechaFinal;
        this.evento=evento;
    }

    // Getters and setters
    public int getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(int idActividad) {
        this.idActividad = idActividad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescricion() {
        return descripcion;
    }

    public void setDescricion(String descricion) {
        this.descripcion = descricion;
    }

    @Override
    public String toString() {
        return "Actividad{" +
                "idActividad=" + idActividad +
                ", nombre='" + nombre + '\'' +
                ", descricion='" + descripcion + '\'' +
                ", fechaInicio='" + fechaInicio + '\'' +
                ", fechaFinal='" + fechaFinal + '\'' +
                '}';
    }
}
