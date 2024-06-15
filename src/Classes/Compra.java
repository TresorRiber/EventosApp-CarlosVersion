package Classes;

public class Compra {
    private int idCompra;
    private int cantidadEntradas;
    private float precio;
    private String fechaCompra;
    private Usuario usuario;
    private Actividad actividad;

    public Compra(int idCompra, int cantidadEntradas, float precio, String fechaCompra, Usuario usuario, Actividad actividad){
        this.idCompra=idCompra;
        this.cantidadEntradas=cantidadEntradas;
        this.precio=precio;
        this.fechaCompra=fechaCompra;
        this.usuario=usuario;
        this.actividad=actividad;
    }

    // Getters and setters
    public int getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
    }

    public int getCantidadEntradas() {
        return cantidadEntradas;
    }

    public void setCantidadEntradas(int cantidadEntradas) {
        this.cantidadEntradas = cantidadEntradas;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(String fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    @Override
    public String toString() {
        return "Compra{" +
                "idCompra=" + idCompra +
                ", cantidadEntradas=" + cantidadEntradas +
                ", precio=" + precio +
                ", fechaCompra='" + fechaCompra + '\'' +
                ", usuario=" + usuario +
                ", actividad=" + actividad +
                '}';
    }
}
