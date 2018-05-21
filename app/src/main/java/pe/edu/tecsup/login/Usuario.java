package pe.edu.tecsup.login;

/**
 * Created by Julio Cesar on 21/03/2018.
 */

public class Usuario {

    private String username;
    private String password;
    private String email;
    private String imagen;
    private boolean estado;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCorreo() {
        return email;
    }

    public void setCorreo(String correo) {
        this.email = correo;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", correo='" + email + '\'' +
                ", imagen='" + imagen + '\'' +
                ", estado=" + estado +
                '}';
    }
}
