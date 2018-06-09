package pe.edu.tecsup.login.Class;
import java.util.Date;

/**
 * Created by Julio Cesar on 20/05/2018.
 */


public class Historial {

    private Integer idinmueble;
    private String direccion;
    private String imagen;
    private String descripcion;
    private String tipo_costo;
    private Double costo;
    private String tipo_inmueble;
    private Integer usuarios_idusuarios;
    private Pivot pivot;


    public Integer getIdinmueble() {
        return idinmueble;
    }

    public void setIdinmueble(Integer idinmueble) {
        this.idinmueble = idinmueble;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
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

    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }

    public String getTipo_inmueble() {
        return tipo_inmueble;
    }

    public void setTipo_inmueble(String tipo_inmueble) {
        this.tipo_inmueble = tipo_inmueble;
    }

    public Integer getUsuarios_idusuarios() {
        return usuarios_idusuarios;
    }

    public void setUsuarios_idusuarios(Integer usuarios_idusuarios) {
        this.usuarios_idusuarios = usuarios_idusuarios;
    }


}
