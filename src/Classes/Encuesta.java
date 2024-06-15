package Classes;

public class Encuesta {
    private int idEncuesta;
    private String fechaFinal;
    private String fechaInicio;
    private String opciones;
    private String pregunta;

    public Encuesta(int idEncuesta, String fechaFinal, String fechaInicio, String opciones, String pregunta){
        this.idEncuesta=idEncuesta;
        this.fechaFinal=fechaFinal;
        this.fechaInicio=fechaFinal;
        this.opciones=opciones;
        this.pregunta=pregunta;
    }

    public int getIdEncuesta() {
        return idEncuesta;
    }

    @Override
    public String toString() {
        return "Encuesta{" +
                "idEncuesta=" + idEncuesta +
                ", fechaFinal='" + fechaFinal + '\'' +
                ", fechaInicio='" + fechaInicio + '\'' +
                ", opciones='" + opciones + '\'' +
                ", pregunta='" + pregunta + '\'' +
                '}';
    }

    public String getPregunta() {
        return pregunta;
    }

    public String getOpciones() {
        return opciones;
    }
}
