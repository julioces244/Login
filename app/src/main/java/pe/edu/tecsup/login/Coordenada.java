package pe.edu.tecsup.login;

/**
 * Created by Julio Cesar on 9/04/2018.
 */

public class Coordenada {

    private int id;
    private String coordenada;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCoordenada() {
        return coordenada;
    }

    public void setCoordenada(String coordenada) {
        this.coordenada = coordenada;
    }

    @Override
    public String toString() {
        return "pe.edu.tecsup.login.Coordenada{" +
                "id=" + id +
                ", coordenada='" + coordenada + '\'' +
                '}';
    }
}
