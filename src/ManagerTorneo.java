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

    //Profundidad actual será igual a la profundidad del nodo en el árbol, está en orden inverso, 0 las hojas y profundidad - 1 la raíz.
    //Identificador será igual al num participantes / 2, generando así un arbol equilibrado.
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
        }
        return p;
    }

    //Si el número de participantes es menor de 2 o no es potencia de dos lanza una excepción
    public int profundidad(int numJug){
        if (numJug < 2 || !isPowerOfTwo(numJug)){
            throw new IllegalArgumentException("El número de participantes debe ser una potencia de dos.");
        }
        int contador = 0;
        while(numJug != 1){
            numJug = numJug/2;
            contador++;
        }
        return contador;
    }


    public void NodosHoja(Partido finalTorneo, List<Partido> lP) {
        if(finalTorneo.getRonda() != 0){
            if(finalTorneo.getPredecesorParticipante1() != null && finalTorneo.getPredecesorParticipante2() != null) {
                NodosHoja(finalTorneo.getPredecesorParticipante1(), lP);
                NodosHoja(finalTorneo.getPredecesorParticipante2(), lP);
            }else{
                throw new IllegalArgumentException("Arbol no válido");
            }
        }else {
            lP.add(finalTorneo);
        }
    }

    public void asignarJugadores(Generación a, List<Participante> participantes, List<Partido> partidos, Torneo t){
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
            NodosHoja(t.getFinal(), partidos);
            asignarJugadores(a, t.getParticipantes(), partidos, t);
            t.setActivo(true);
        }else if(t == null) {
            throw new IllegalArgumentException("Torneo no encontrado.");
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
        return buscarPartidoAuxiliar(t.getFinal(), partido);
    }

    private  Partido buscarPartidoAuxiliar(Partido p, int partido) {
        if(p.getId() == partido) {  //Caso de ser la final
            return p;
        }
        Partido resultadoIzquierdo = buscarPartidoAuxiliar(p.getPredecesorParticipante1(), partido);  //si es un nodo hoja sería nulo?
        if (resultadoIzquierdo != null) {
            return resultadoIzquierdo;
        }
        Partido resultadoDerecho = buscarPartidoAuxiliar(p.getPredecesorParticipante2(), partido);
        if (resultadoDerecho != null) {
            return resultadoDerecho;
        }
        return null;
    }

    public void informacionPartidoTorneo(Torneo t){
        if(t.isActivo()){
            int iterator = 0;
            Partido p = t.getFinal();
            imprimirInformacion(p, iterator);
        }else{
            System.out.println("Torneo no empezado.");
        }
    }

    private void imprimirInformacion(Partido p, int iterator){
        repetir(iterator);
        switch (p.getFase()){
            case ESPERANDO -> System.out.print("Partido " + p.getId() + ": en espera. Predecesores: {\n");
            case NO_JUGADO -> System.out.print("Partido " + p.getId() + ": preparado, entre " + p.getParticipante1().getName() + " y " + p.getParticipante2().getName()
                    + ". Predecesores: {\n");
            case EN_JUEGO -> System.out.print("Partido " + p.getId() + ": jugándose entre " + p.getParticipante1().getName() + " y " + p.getParticipante2().getName() + ". Predecesores: {\n");
            case FINALIZADO -> System.out.print("Partido " + p.getId() + ": finalizado, jugado entre " + p.getParticipante1().getName() + " y " + p.getParticipante2().getName()
                    +  ". Predecesores: {\n");
        }
        if(p.getPredecesorParticipante1() != null){
            imprimirInformacion(p.getPredecesorParticipante1(), iterator + 1);
            imprimirInformacion(p.getPredecesorParticipante2(), iterator + 1);
        }else{
            repetir(iterator + 1);
            if(p.getParticipante1().getisSeed()){
                System.out.print("\t" + p.getParticipante1().getName() + ", de " + p.getParticipante1().getAge() + " años, cabeza de serie.\n");
            }else{
                System.out.print("\t" + p.getParticipante1().getName() + ", de " + p.getParticipante1().getAge() + " años.\n");
            }
            repetir(iterator + 1);
            if(p.getParticipante2().getisSeed()){
                System.out.print("\t" + p.getParticipante2().getName() + ", de " + p.getParticipante2().getAge() + " años, cabeza de serie.\n");
            }else{
                System.out.print("\t" + p.getParticipante2().getName() + ", de " + p.getParticipante2().getAge() + " años.\n");
            }
        }
        repetir(iterator);
        if(Fase.FINALIZADO == p.getFase()){
            System.out.print("} Ganador: " + p.getGanador().getName() + ". (fin resumen partido " + p.getId() + ")\n");
        }else {
            System.out.print("} (fin resumen partido " + p.getId() + ")\n");
        }
    }

    private void repetir(int iterator){
        for(int i = 0; i < iterator; i++){
            System.out.print("\t");
        }
    }

    private boolean isPowerOfTwo(int n) {return (n & (n - 1)) == 0;}
}