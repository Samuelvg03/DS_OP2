import java.util.List;
public class GeneraciónUtilizada {
    private final Generación a;

    GeneraciónUtilizada(Generación a) {
        this.a = a;
    }

    public void realizarGeneración(List<Participante> participantes, List<Partido> partidos) {
        switch (this.a) {
            case ALEATORIO -> {
                GeneraciónAleatoria e = new GeneraciónAleatoria();
                e.crearPartidos(participantes, partidos);
            }
            case CABEZADESERIE -> {
                GeneraciónCabezaSerie c = new GeneraciónCabezaSerie();
                c.crearPartidos(participantes, partidos);
            }
        }
    }
}
