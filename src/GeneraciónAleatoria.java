import java.util.ArrayList;
import java.util.List;

public final class GeneraciónAleatoria implements GenerarCuadros{
    int x;
    List<Integer> numeros = new ArrayList<>();
    public void crearPartidos(List<Participante> participantes, List<Partido> partidos){
        x = participantes.size()/2;
        for (int i = 0; i < x; i++) {
            int participant1 = (int) (Math.random() * participantes.size());
            int participant2 = (int) (Math.random() * participantes.size());
            while (numeros.contains(participant1)){
                participant1 = (int) (Math.random() * participantes.size());
            }
            numeros.add(participant1);
            while (numeros.contains(participant2)){
                participant2 = (int) (Math.random() * participantes.size());
            }
            numeros.add(participant2);
            partidos.get(i).setParticipante1(participantes.get(participant1));
            partidos.get(i).setParticipante2(participantes.get(participant2));
            partidos.get(i).setFase(Fase.NO_JUGADO);
        }
    }

    @Override
    public void realizarGeneración(List<Participante> participantes, List<Partido> partidos) {

    }
}
