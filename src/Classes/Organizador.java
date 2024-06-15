package Classes;

public class Organizador {
    private int idOrganizador;
    private String email;
    private String nombre;
    private String telefono;

    public Organizador(int idOrganizador, String email, String nombre, String telefono){
        this.idOrganizador=idOrganizador;
        this.email=email;
        this.nombre=nombre;
        this.telefono=telefono;
    }

    // Getters and setters
    public int getIdOrganizador() {
        return idOrganizador;
    }

    public void setIdOrganizador(int idOrganizador) {
        this.idOrganizador = idOrganizador;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Organizador{" +
                "idOrganizador=" + idOrganizador +
                ", email='" + email + '\'' +
                ", nombre='" + nombre + '\'' +
                ", telefono='" + telefono + '\'' +
                '}';
    }
}
