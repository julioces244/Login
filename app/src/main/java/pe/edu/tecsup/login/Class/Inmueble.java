package pe.edu.tecsup.login.Class;

/**
 * Created by Julio Cesar on 15/04/2018.
 */

public class Inmueble {

    private int idinmueble;
    private String direccion;
    private String coordenadas;
    private String imagen;
    private String descripcion;
    private String tipo_costo;
    private double costo;
    private String capacidad_max;
    private String tipo_inmueble;
    private int estado;
    private int usuarios_idusuarios;
    private int ranking_idranking;


    public int getIdinmueble() {
        return idinmueble;
    }

    public void setIdinmueble(int idinmueble) {
        this.idinmueble = idinmueble;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(String coordenadas) {
        this.coordenadas = coordenadas;
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

    public String getTipo_costo() {
        return tipo_costo;
    }

    public void setTipo_costo(String tipo_costo) {
        this.tipo_costo = tipo_costo;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public String getCapacidad_max() {
        return capacidad_max;
    }

    public void setCapacidad_max(String capacidad_max) {
        this.capacidad_max = capacidad_max;
    }

    public String getTipo_inmueble() {
        return tipo_inmueble;
    }

    public void setTipo_inmueble(String tipo_inmueble) {
        this.tipo_inmueble = tipo_inmueble;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getUsuarios_idusuarios() {
        return usuarios_idusuarios;
    }

    public void setUsuarios_idusuarios(int usuarios_idusuarios) {
        this.usuarios_idusuarios = usuarios_idusuarios;
    }

    public int getRanking_idranking() {
        return ranking_idranking;
    }

    public void setRanking_idranking(int ranking_idranking) {
        this.ranking_idranking = ranking_idranking;
    }

    @Override
    public String toString() {
        return "Inmueble{" +
                "idinmueble=" + idinmueble +
                ", direccion='" + direccion + '\'' +
                ", coordenadas='" + coordenadas + '\'' +
                ", imagen='" + imagen + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", tipo_costo='" + tipo_costo + '\'' +
                ", costo=" + costo +
                ", capacidad_max='" + capacidad_max + '\'' +
                ", tipo_inmueble='" + tipo_inmueble + '\'' +
                ", estado=" + estado +
                ", usuarios_idusuarios=" + usuarios_idusuarios +
                ", ranking_idranking=" + ranking_idranking +
                '}';
    }
}
