package pe.edu.tecsup.login.Class;

/**
 * Created by Julio Cesar on 29/04/2018.
 */

public class Ranking {

    private int idranking;
    private String puntaje;
    private String comentario;
    private int usuarios_idusuarios;


    public int getIdranking() {
        return idranking;
    }

    public void setIdranking(int idranking) {
        this.idranking = idranking;
    }

    public String getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(String puntaje) {
        this.puntaje = puntaje;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public int getUsuarios_idusuarios() {
        return usuarios_idusuarios;
    }

    public void setUsuarios_idusuarios(int usuarios_idusuarios) {
        this.usuarios_idusuarios = usuarios_idusuarios;
    }


    @Override
    public String toString() {
        return "Ranking{" +
                "idranking=" + idranking +
                ", puntaje='" + puntaje + '\'' +
                ", comentario='" + comentario + '\'' +
                ", usuarios_idusuarios=" + usuarios_idusuarios +
                '}';
    }
}
