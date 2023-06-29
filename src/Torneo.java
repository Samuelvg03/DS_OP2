import java.util.ArrayList;
import java.util.List;

public class Torneo {

    private String nombre;
    private Partido Final; //Actuará como nodo raíz del árbol de partidos.
    private List<Participante> participantes = new ArrayList<>();
    private boolean activo;


    //Crea torneo vacío para n jugadores.
    public Torneo(String nombre, boolean activo){
        this.nombre = nombre;
        this.activo = activo;
    }

    public void setFinal(Partido Final) {
        this.Final = Final;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Participante> getParticipantes() {
        return participantes;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Partido getFinal() {
        return Final;
    }
}
