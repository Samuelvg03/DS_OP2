import java.util.List;

public sealed interface GenerarCuadros permits GeneraciónUtilizada, GeneraciónCabezaSerie, GeneraciónAleatoria{
    public void crearPartidos(List<Participante> participantes, List<Partido> partidos);
    public void realizarGeneración(List<Participante> participantes, List<Partido> partidos);
}