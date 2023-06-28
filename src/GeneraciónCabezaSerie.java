import java.util.ArrayList;
import java.util.List;

public final class GeneraciónCabezaSerie implements GenerarCuadros{
    public void crearPartidos(List<Participante> participantes, List<Partido> partidos){

        List<Participante> cabSerie = buscarCabSerie(participantes);

        if(cabSerie.isEmpty()){
            GeneraciónUtilizada a = new GeneraciónUtilizada(Generación.ALEATORIO);
            a.realizarGeneración(participantes, partidos);
            return;
        }
        if(cabSerie.size()< participantes.size()/2){
            auxTablaCab1(participantes, cabSerie, partidos);
        }else if(cabSerie.size() == participantes.size()/2){
            auxTablaCab2(participantes, cabSerie, partidos);
        }else{
            GeneraciónUtilizada a = new GeneraciónUtilizada(Generación.ALEATORIO);
            a.realizarGeneración(participantes, partidos);
        }
    }

    private List<Participante> buscarCabSerie(List<Participante> participantes){
        int x = participantes.size();
        List<Participante> cabSerie = new ArrayList<>();
        for(int i = 0; i < x; i++){
            if(participantes.get(i).getisSeed()){
                cabSerie.add(participantes.get(i));
            }
        }
        return cabSerie;
    }

    private void auxTablaCab1(List<Participante> participantes, List<Participante> cabSerie, List<Partido> partiRonda1){
        int aux, aux1, i = 0, j, k = 0, l;
        int x = cabSerie.size();
        List<Integer> numeros = new ArrayList<>();

        for(i = 0; i < x; i++){
            aux = (int) (Math.random() * participantes.size());
            while (numeros.contains(aux) ||!participantes.get(aux).getisSeed()){
                aux = (int) (Math.random() * participantes.size());
            }
            numeros.add(aux);
            partiRonda1.get(i).setParticipante1(participantes.get(aux));
            partiRonda1.get(i).setFase(Fase.NO_JUGADO);
        }

        for(j = 0; j != i; j++){
            aux1 = (int) (Math.random() * participantes.size());
            while (numeros.contains(aux1) || participantes.get(aux1).getisSeed()){
                aux1 = (int) (Math.random() * participantes.size());
            }
            numeros.add(aux1);
            partiRonda1.get(j).setParticipante2(participantes.get(aux1));
        }

        int p = i;
        int y = ((participantes.size() - (cabSerie.size() * 2))/2) + p;

        for(l = i; l < y; l++){
            aux = (int) (Math.random() * participantes.size());
            aux1 = (int) (Math.random() * participantes.size());
            while (numeros.contains(aux)){
                aux = (int) (Math.random() * participantes.size());
            }
            numeros.add(aux);
            while (numeros.contains(aux1) || participantes.get(aux1).getisSeed()){
                aux1 = (int) (Math.random() * participantes.size());
            }
            numeros.add(aux1);
            partiRonda1.get(l).setParticipante1(participantes.get(aux));
            partiRonda1.get(l).setParticipante2(participantes.get(aux1));
            partiRonda1.get(l).setFase(Fase.NO_JUGADO);
        }
    }

    private void auxTablaCab2(List<Participante> participantes, List<Participante> cabSerie, List<Partido> partiRonda1){
        int aux, aux1, i;
        int x = participantes.size()/2;
        List<Integer> numeros = new ArrayList<>();
        for(i = 0; i < x; i ++){
            aux = (int) (Math.random() * participantes.size());
            aux1 = (int) (Math.random() * participantes.size());
            while (numeros.contains(aux) || !participantes.get(aux).getisSeed()){
                aux = (int) (Math.random() * participantes.size());
            }
            numeros.add(aux);
            while(numeros.contains(aux1) || participantes.get(aux1).getisSeed()){
                aux1 = (int) (Math.random() * participantes.size());
            }
            numeros.add(aux1);
            partiRonda1.get(i).setParticipante1(participantes.get(aux));
            partiRonda1.get(i).setParticipante2(participantes.get(aux1));
            partiRonda1.get(i).setFase(Fase.NO_JUGADO);
        }
    }

}

