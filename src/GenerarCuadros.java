import java.util.List;

public sealed interface GenerarCuadros permits GeneraciónCabezaSerie, GeneraciónAleatoria{
    public void crearPartidos(List<Participante> participantes, List<Partido> partiRonda1);
}