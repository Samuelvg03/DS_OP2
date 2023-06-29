import java.util.ArrayList;
import java.util.List;

public final class GeneraciónCabezaSerie implements GenerarCuadros{
    int x, p, y;
    int aux, aux1, i = 0, j, k = 0, l;
    List<Integer> numeros = new ArrayList<>();
    List<Participante> isSeed = new ArrayList<>();
    public void crearPartidos(List<Participante> participantes, List<Partido> partidos){

        List<Participante> isSeed = buscarIsSeed(participantes);

        if(isSeed.isEmpty()){
            GeneraciónUtilizada a = new GeneraciónUtilizada(Generación.ALEATORIO);
            a.realizarGeneración(participantes, partidos);
            return;
        }
        if(isSeed.size()< participantes.size()/2){
            tablaAux1(participantes, isSeed, partidos);
        }else if(isSeed.size() == participantes.size()/2){
            tablaAux2(participantes, partidos);
        }else{
            GeneraciónUtilizada a = new GeneraciónUtilizada(Generación.ALEATORIO);
            a.realizarGeneración(participantes, partidos);
        }
    }


    private List<Participante> buscarIsSeed(List<Participante> participantes){
        x = participantes.size();
        for(int i = 0; i < x; i++){
            if(participantes.get(i).getIsSeed()){
                isSeed.add(participantes.get(i));
            }
        }
        return isSeed;
    }

    private void tablaAux1(List<Participante> participantes, List<Participante> isSeed, List<Partido> partidos){
        x = isSeed.size();

        for(i = 0; i < x; i++){
            aux = (int) (Math.random() * participantes.size());
            while (numeros.contains(aux) ||!participantes.get(aux).getIsSeed()){
                aux = (int) (Math.random() * participantes.size());
            }
            numeros.add(aux);
            partidos.get(i).setParticipante1(participantes.get(aux));
            partidos.get(i).setFase(Fase.NO_JUGADO);
        }

        for(j = 0; j != i; j++){
            aux1 = (int) (Math.random() * participantes.size());
            while (numeros.contains(aux1) || participantes.get(aux1).getIsSeed()){
                aux1 = (int) (Math.random() * participantes.size());
            }
            numeros.add(aux1);
            partidos.get(j).setParticipante2(participantes.get(aux1));
        }

        p = i;
        y = ((participantes.size() - (isSeed.size() * 2))/2) + p;

        for(l = i; l < y; l++){
            aux = (int) (Math.random() * participantes.size());
            aux1 = (int) (Math.random() * participantes.size());
            while (numeros.contains(aux)){
                aux = (int) (Math.random() * participantes.size());
            }
            numeros.add(aux);
            while (numeros.contains(aux1) || participantes.get(aux1).getIsSeed()){
                aux1 = (int) (Math.random() * participantes.size());
            }
            numeros.add(aux1);
            partidos.get(l).setParticipante1(participantes.get(aux));
            partidos.get(l).setParticipante2(participantes.get(aux1));
            partidos.get(l).setFase(Fase.NO_JUGADO);
        }
    }

    private void tablaAux2(List<Participante> participantes, List<Partido> partidos){
        x = participantes.size()/2;
        for(i = 0; i < x; i ++){
            aux = (int) (Math.random() * participantes.size());
            aux1 = (int) (Math.random() * participantes.size());
            while (numeros.contains(aux) || !participantes.get(aux).getIsSeed()){
                aux = (int) (Math.random() * participantes.size());
            }
            numeros.add(aux);
            while(numeros.contains(aux1) || participantes.get(aux1).getIsSeed()){
                aux1 = (int) (Math.random() * participantes.size());
            }
            numeros.add(aux1);
            partidos.get(i).setParticipante1(participantes.get(aux));
            partidos.get(i).setParticipante2(participantes.get(aux1));
            partidos.get(i).setFase(Fase.NO_JUGADO);
        }
    }

    @Override
    public void realizarGeneración(List<Participante> participantes, List<Partido> partidos) {
    }
}

