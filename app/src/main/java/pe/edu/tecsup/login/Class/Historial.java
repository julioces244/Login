package pe.edu.tecsup.login.Class;
import java.util.Date;

/**
 * Created by Julio Cesar on 20/05/2018.
 */


public class Historial {


    private int usuarios_idusuarios;
    private int inmueble_idinmueble;
    private Date fecha_usuario;


    public int getUsuarios_idusuarios() {
        return usuarios_idusuarios;
    }

    public void setUsuarios_idusuarios(int usuarios_idusuarios) {
        this.usuarios_idusuarios = usuarios_idusuarios;
    }

    public int getInmueble_idinmueble() {
        return inmueble_idinmueble;
    }

    public void setInmueble_idinmueble(int inmueble_idinmueble) {
        this.inmueble_idinmueble = inmueble_idinmueble;
    }

    public Date getFecha_usuario() {
        return fecha_usuario;
    }

    public void setFecha_usuario(Date fecha_usuario) {
        this.fecha_usuario = fecha_usuario;
    }

    @Override
    public String toString() {
        return "Historial{" +
                "usuarios_idusuarios=" + usuarios_idusuarios +
                ", inmueble_idinmueble=" + inmueble_idinmueble +
                ", fecha_usuario=" + fecha_usuario +
                '}';
    }
}
