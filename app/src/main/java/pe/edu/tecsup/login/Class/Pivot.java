package pe.edu.tecsup.login.Class;

import java.util.Date;

/**
 * Created by Julio Cesar on 2/06/2018.
 */

public class Pivot {

    private Integer usuarios_idusuarios;
    private Integer inmueble_idinmueble;
    private Date fecha_usuario;


    public Integer getUsuarios_idusuarios() {
        return usuarios_idusuarios;
    }

    public void setUsuarios_idusuarios(Integer usuarios_idusuarios) {
        this.usuarios_idusuarios = usuarios_idusuarios;
    }

    public Integer getInmueble_idinmueble() {
        return inmueble_idinmueble;
    }

    public void setInmueble_idinmueble(Integer inmueble_idinmueble) {
        this.inmueble_idinmueble = inmueble_idinmueble;
    }

    public Date getFecha_usuario() {
        return fecha_usuario;
    }

    public void setFecha_usuario(Date fecha_usuario) {
        this.fecha_usuario = fecha_usuario;
    }
}
