public class Main {

    public static void main(String[] args) {
        //TORNEO SIN CABEZAS DE SERIE
        ManagerTorneo m = new ManagerTorneo();
        m.crearTorneo("Ejemplo");

        Participante jugador1 = new Participante("Juan", 24, false);
        Participante jugador2 = new Participante("Eloy", 18, false);
        Participante jugador3 = new Participante("Samu", 19, false);
        Participante jugador4 = new Participante("Teo", 20, false);

        m.anadirParticipantes(jugador1, "Ejemplo");
        m.anadirParticipantes(jugador2, "Ejemplo");
        m.anadirParticipantes(jugador3, "Ejemplo");
        m.anadirParticipantes(jugador4, "Ejemplo");

        Generación a = Generación.CABEZADESERIE;
        m.empezarTorneo("Ejemplo", a);

        Partido p1;
        p1 = m.buscarPartido(m.buscarTorneo("Ejemplo"), 2);  //Sigue una disposicion e AVL para 4 participantes -> raiz 2 nodo izq 1 nodo der 2.
        p1.cambiarFase(null);          //Empezamos el partido.
        p1.cambiarFase(p1.getParticipante2());        //Asignamos un ganador, en este caso adv2.


        m.informacionPartidoTorneo(m.buscarTorneo( "Ejemplo"));
/*
        //TORNEO CON CABEZAS DE SERIE < PARTICIPANTES / 2
        m.crearTorneo("FÚTBOL");

        Jugador jugador5 = new Jugador("Pepe", 26, true);


        m.anadirParticipantes(jugador5, "FÚTBOL");
        m.anadirParticipantes(jugador2, "FÚTBOL");
        m.anadirParticipantes(jugador3, "FÚTBOL");
        m.anadirParticipantes(jugador4, "FÚTBOL");

        m.empezarTorneo("FÚTBOL", a);

        Partido p5;
        p5 = m.buscarPartido(m.buscarTorneo("FÚTBOL"), 2);  //Sigue una disposicion e AVL para 4 participantes -> raiz 2 nodo izq 1 nodo der 2.
        p5.cambiarEstado(null);          //Empezamos el partido.
        p5.cambiarEstado(p5.getAdversario2());        //Asignamos un ganador, en este caso adv2.


        m.informacionPartidoTorneo(m.buscarTorneo( "FÚTBOL"));

        //TORNEO CON CABEZAS DE SERIE = PARTICIPANTES / 2
        m.crearTorneo("ELOY");

        Jugador jugador6 = new Jugador("Carla", 22, true);

        m.anadirParticipantes(jugador5, "ELOY");
        m.anadirParticipantes(jugador6, "ELOY");
        m.anadirParticipantes(jugador3, "ELOY");
        m.anadirParticipantes(jugador4, "ELOY");

        m.empezarTorneo("ELOY", a);

        Partido p8;
        p8 = m.buscarPartido(m.buscarTorneo("ELOY"), 2);  //Sigue una disposicion e AVL para 4 participantes -> raiz 2 nodo izq 1 nodo der 2.
        p8.cambiarEstado(null);          //Empezamos el partido.
        p8.cambiarEstado(p8.getAdversario2());        //Asignamos un ganador, en este caso adv2.


        m.informacionPartidoTorneo(m.buscarTorneo( "ELOY"));

        //TORNEO CON CABEZAS DE SERIE > PARTICIPANTES / 2
        m.crearTorneo("MAMÁ");

        Jugador jugador7 = new Jugador("Ana", 48, true);

        m.anadirParticipantes(jugador5, "MAMÁ");
        m.anadirParticipantes(jugador6, "MAMÁ");
        m.anadirParticipantes(jugador7, "MAMÁ");
        m.anadirParticipantes(jugador4, "MAMÁ");

        m.empezarTorneo("MAMÁ", a);

        Partido p10;
        p10 = m.buscarPartido(m.buscarTorneo("MAMÁ"), 2);  //Sigue una disposicion e AVL para 4 participantes -> raiz 2 nodo izq 1 nodo der 2.
        p10.cambiarEstado(null);          //Empezamos el partido.
        p10.cambiarEstado(p10.getAdversario1());        //Asignamos un ganador, en este caso adv2.


        m.informacionPartidoTorneo(m.buscarTorneo( "MAMÁ"));

*/
    }


}

