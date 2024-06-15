package Classes;

public class Comentario {
    private int idComentario;
    private int puntuacion;
    private String fechaPublicacion;
    private String texto;
    private Evento evento;
    private Usuario usuario;

    public Comentario(int idComentario, int puntuacion, String fechaPublicacion, String texto, Evento evento, Usuario usuario){
        this.idComentario=idComentario;
        this.puntuacion=puntuacion;
        this.fechaPublicacion=fechaPublicacion;
        this.texto=texto;
        this.evento=evento;
        this.usuario=usuario;
    }

    // Getters and setters
    public void setIdComentario(int idComentario) {
        this.idComentario = idComentario;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public String getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(String fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    @Override
    public String toString() {
        return "Comentario{" +
                "idComentario=" + idComentario +
                ", puntuacion=" + puntuacion +
                ", fechaPublicacion='" + fechaPublicacion + '\'' +
                ", texto='" + texto + '\'' +
                ", evento=" + evento +
                ", usuario=" + usuario +
                '}';
    }
}
