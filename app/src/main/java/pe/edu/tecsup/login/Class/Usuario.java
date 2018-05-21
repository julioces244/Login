package pe.edu.tecsup.login.Class;

/**
 * Created by Julio Cesar on 21/03/2018.
 */

public class Usuario {

    private int idusuario;
    private String nombre;
    private String apellido;
    private String correo;
    private String username;
    private String password;
    private String imagen;
    private String descripcion;
    private String genero;
    private int num_documento;
    private String tipo_documento;
    private String telefono;

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

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

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getNum_documento() {
        return num_documento;
    }

    public void setNum_documento(int num_documento) {
        this.num_documento = num_documento;
    }

    public String getTipo_documento() {
        return tipo_documento;
    }

    public void setTipo_documento(String tipo_documento) {
        this.tipo_documento = tipo_documento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "idusuario=" + idusuario +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", correo='" + correo + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", imagen='" + imagen + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", genero='" + genero + '\'' +
                ", num_documento=" + num_documento +
                ", tipo_documento='" + tipo_documento + '\'' +
                ", telefono='" + telefono + '\'' +
                '}';
    }
}
