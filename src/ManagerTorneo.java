import java.util.ArrayList;
import java.util.List;

public class ManagerTorneo {
    private List<Torneo> torneosActivos = new ArrayList<>();

    public List<Torneo> getTorneosActivos() {
        return torneosActivos;
    }
    public void setTorneosActivos(List<Torneo> torneosActivos) {
        this.torneosActivos = torneosActivos;
    }

    int contador;
    //Partido resultadoIzquierdo, resultadoDerecho;


    //La raíz tendrá el identificador 1, aumentando en uno cada partido a mayores, siendo así 2 y 3 las semifinales
    //y así sucesivamente. La profundidad será 0 para las hojas e irá aumentando para las ssucesivas fases.
    public Partido crearPartidosTorneo(int identificador, int profundidadActual){
        Partido p = new Partido();
        profundidadActual--;
        p.setRonda(profundidadActual);
        p.setId(identificador);
        p.setFase(Fase.ESPERANDO);
        if(profundidadActual > 0){
            p.setPredecesorParticipante1(crearPartidosTorneo(identificador * 2 , profundidadActual));
            p.getPredecesorParticipante1().attach(p);
            p.setPredecesorParticipante2(crearPartidosTorneo(identificador * 2 + 1, profundidadActual));
            p.getPredecesorParticipante2().attach(p);
            /*
            p.setAntecesorParticipante1(crearPartidosTorneo(identificador / 2 , profundidadActual + 1));
            p.getAntecesorParticipante1().attach(p);
            p.setAntecesorParticipante2(crearPartidosTorneo(identificador / 2 - 1, profundidadActual + 1));
            p.getAntecesorParticipante2().attach(p);*/
        }
        return p;
    }

    //Si el número de participantes es menor de 2 o no es potencia de dos lanza una excepción
    public int profundidad(int numJug){
        if (numJug < 2 || !isPowerOfTwo(numJug)){
            throw new IllegalArgumentException("El número de participantes debe ser una potencia de dos.");
        }
        contador = 0;
        while(numJug != 1){
            numJug = numJug/2;
            contador++;
        }
        return contador;
    }


    public void nodosHoja(Partido finalTorneo, List<Partido> listPa) {
        if(finalTorneo.getRonda() != 0){
            if(finalTorneo.getPredecesorParticipante1() != null && finalTorneo.getPredecesorParticipante2() != null) {
                nodosHoja(finalTorneo.getPredecesorParticipante1(), listPa);
                nodosHoja(finalTorneo.getPredecesorParticipante2(), listPa);
            }else{
                throw new IllegalArgumentException("Arbol no válido");
            }
        }else {
            listPa.add(finalTorneo);
        }
    }

    public void asignarJugadores(Generación a, List<Participante> participantes, List<Partido> partidos, Torneo torneo){
        GeneraciónUtilizada e = new GeneraciónUtilizada(a);
        e.realizarGeneración(participantes, partidos);
    }

    public void anadirParticipantes(Participante j, String nombreTorneo)  {
        Torneo t = buscarTorneo(nombreTorneo);
        if(t != null && !t.isActivo()) {
            t.getParticipantes().add(j);
        }else {
            throw new IllegalArgumentException("Torneo empezado.");
        }
    }
    //nada q cambiar
    public void crearTorneo(String nombre){
        Torneo t = new Torneo(nombre, false); //Iniciamos el torneo como no empezado.
        torneosActivos.add(t);
    }
    //cambié añadiendo la condición si no es divisible por 2 (PUEDO METER EL DE POTENCIADE2) y el crear partidos.Tmb el MyException
    public void empezarTorneo(String nombre, Generación a) {
        Torneo t = buscarTorneo(nombre);
        if(t != null && (t.getParticipantes().size() % 2 != 0)){
            throw new IllegalArgumentException("El número de participantes es impar.");
        }else if(t != null && !(t.getParticipantes().size() == 0)) {
            t.setFinal(crearPartidosTorneo(1, profundidad(t.getParticipantes().size())));
            List<Partido> partidos = new ArrayList<>();
            nodosHoja(t.getFinal(), partidos);
            asignarJugadores(a, t.getParticipantes(), partidos, t);
            t.setActivo(true);
        }else if(t == null) {
            throw new IllegalArgumentException("No existe el torneo.");
        }else{
            throw new IllegalArgumentException("Torneo sin participantes.");
        }
    }

    public Torneo buscarTorneo(String nombreTor){
        for(Torneo aux : this.torneosActivos){
            if(aux.getNombre().equals(nombreTor)){
                return aux;
            }
        }
        return null;
    }

    public Partido buscarPartido(Torneo t, int partido) {
        return buscarAuxiliar(t, t.getFinal(), partido);
    }

    public Partido buscarPartido2(Torneo t, int partido){
        return buscarAuxiliar2(t, t.getFinal(), partido);
    }

    private Partido buscarAuxiliar2(Torneo t, Partido p, int partido){
        if(p.getId() == partido) {  //Caso de ser la final
            return p;
        }
        if(buscarAuxiliar(t, p.getPredecesorParticipante2(), partido) != null){
            return buscarAuxiliar(t, p.getPredecesorParticipante2(), partido);
        }
        return null;
    }

    private  Partido buscarAuxiliar(Torneo t, Partido p, int partido) {
        if(p.getId() == partido) {  //Caso de ser la final
            return p;
        }

        Partido hijoIzq = p.getPredecesorParticipante1();
        Partido hijoDer = p.getPredecesorParticipante2();
        if(hijoIzq.getId() == partido) {
            return buscarAuxiliar(t, p.getPredecesorParticipante1(), partido);
        }else{
            if(hijoDer.getId() == partido){
                return buscarAuxiliar(t, p.getPredecesorParticipante2(), partido);
            }else{
                if (buscarAuxiliar(t, p.getPredecesorParticipante1(), partido) == null &&
                        (buscarAuxiliar(t, p.getPredecesorParticipante2(), partido) == null)) {

                    return buscarPartido2(t, partido);
                }else{
                    return buscarAuxiliar(t, p.getPredecesorParticipante1(), partido);
                }
            }

        }
/*
        Partido resultadoIzquierdo = buscarAuxiliar(p.getPredecesorParticipante1(), partido);  //si es un nodo hoja sería nulo?
        if (resultadoIzquierdo != null) {
            return resultadoIzquierdo;
        }
        Partido resultadoDerecho = buscarAuxiliar(p.getPredecesorParticipante2(), partido);
        if (resultadoDerecho != null) {
            return resultadoDerecho;
        }
*/

    }

    public void informacionPartidoTorneo(Torneo t){
        if(t.isActivo()){
            contador = 0;
            Partido p = t.getFinal();
            imprimirInformacion(p, contador);
        }else{
            System.out.println("El torneo no fue inicializado.");
        }
    }

    private void imprimirInformacion(Partido p, int contador){
        repetir(contador);
        switch (p.getFase()){
            case ESPERANDO -> System.out.print("Partido " + p.getId() + ": en espera. Predecesores: {\n");
            case NO_JUGADO -> System.out.print("Partido " + p.getId() + ": preparado, entre " + p.getParticipante1().getName() + " y " + p.getParticipante2().getName()
                    + ". Predecesores: {\n");
            case EN_JUEGO -> System.out.print("Partido " + p.getId() + ": jugándose entre " + p.getParticipante1().getName() + " y " + p.getParticipante2().getName() + ". Predecesores: {\n");
            case FINALIZADO -> System.out.print("Partido " + p.getId() + ": finalizado, jugado entre " + p.getParticipante1().getName() + " y " + p.getParticipante2().getName()
                    +  ". Predecesores: {\n");
        }
        if(p.getPredecesorParticipante1() != null){
            imprimirInformacion(p.getPredecesorParticipante1(), contador + 1);
            imprimirInformacion(p.getPredecesorParticipante2(), contador + 1);
        }else{
            repetir(contador + 1);
            if(p.getParticipante1().getIsSeed()){
                System.out.print("\t" + p.getParticipante1().getName() + ", de " + p.getParticipante1().getAge() + " años, cabeza de serie.\n");
            }else{
                System.out.print("\t" + p.getParticipante1().getName() + ", de " + p.getParticipante1().getAge() + " años.\n");
            }
            repetir(contador + 1);
            if(p.getParticipante2().getIsSeed()){
                System.out.print("\t" + p.getParticipante2().getName() + ", de " + p.getParticipante2().getAge() + " años, cabeza de serie.\n");
            }else{
                System.out.print("\t" + p.getParticipante2().getName() + ", de " + p.getParticipante2().getAge() + " años.\n");
            }
        }
        repetir(contador);
        if(Fase.FINALIZADO == p.getFase()){
            System.out.print("} Ganador: " + p.getGanador().getName() + ". (fin resumen partido " + p.getId() + ")\n");
        }else {
            System.out.print("} (fin resumen partido " + p.getId() + ")\n");
        }
    }

    private void repetir(int contador){
        for(int i = 0; i < contador; i++){
            System.out.print("\t");
        }
    }

    private boolean isPowerOfTwo(int n) {return (n & (n - 1)) == 0;}
}