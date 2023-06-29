import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class GeneracionTest {

    @Test
    public void testGeneracionAleatoria() {
        List<Participante> participantes = new ArrayList<>();
        participantes.add(new Participante("Participante 1", 18, false));
        participantes.add(new Participante("Participante 2", 24, false));
        participantes.add(new Participante("Participante 3", 19, false));
        participantes.add(new Participante("Participante 4", 34, true));

        List<Partido> partidos = new ArrayList<>();
        partidos.add(new Partido());
        partidos.add(new Partido());

        GenerarCuadros generador = new GeneraciónAleatoria();
        generador.crearPartidos(participantes, partidos);

        Assertions.assertEquals(Fase.NO_JUGADO, partidos.get(0).getFase());
        Assertions.assertEquals(Fase.NO_JUGADO, partidos.get(1).getFase());

        // Verificar que los participantes asignados a los partidos son diferentes
        Assertions.assertNotEquals(partidos.get(0).getParticipante1(), partidos.get(0).getParticipante2());
        Assertions.assertNotEquals(partidos.get(1).getParticipante1(), partidos.get(1).getParticipante2());
    }

    @Test
    public void testGeneracionCabezaSerie() {
        List<Participante> participantes = new ArrayList<>();
        Participante cabezaSerie1 = new Participante("Cabeza de Serie 1", 19, true);
        cabezaSerie1.setSeed(true);
        Participante cabezaSerie2 = new Participante("Cabeza de Serie 2", 18, true);
        cabezaSerie2.setSeed(true);
        participantes.add(cabezaSerie1);
        participantes.add(new Participante("Participante 2", 44, false));
        participantes.add(new Participante("Participante 3", 16, false));
        participantes.add(cabezaSerie2);

        List<Partido> partidos = new ArrayList<>();
        partidos.add(new Partido());
        partidos.add(new Partido());

        GenerarCuadros generador = new GeneraciónCabezaSerie();
        generador.crearPartidos(participantes, partidos);

        Assertions.assertEquals(Fase.NO_JUGADO, partidos.get(0).getFase());
        Assertions.assertEquals(Fase.NO_JUGADO, partidos.get(1).getFase());

        // Verificar que los participantes asignados a los partidos son diferentes
        Assertions.assertNotEquals(partidos.get(0).getParticipante1(), partidos.get(0).getParticipante2());
        Assertions.assertNotEquals(partidos.get(1).getParticipante1(), partidos.get(1).getParticipante2());

        // Verificar que los participantes cabeza de serie no se enfrentan entre si
        Assertions.assertFalse(partidos.get(0).getParticipante1().getIsSeed() && partidos.get(0).getParticipante2().getIsSeed());
        Assertions.assertFalse(partidos.get(1).getParticipante1().getIsSeed() && partidos.get(1).getParticipante2().getIsSeed());
    }

    @Test
    public void testGeneracionCabezaSerieSinCabezasDeSerie() {
        List<Participante> participantes = new ArrayList<>();
        participantes.add(new Participante("Participante 1", 18, false));
        participantes.add(new Participante("Participante 2", 24, false));
        participantes.add(new Participante("Participante 3", 38, false));
        participantes.add(new Participante("Participante 4", 25, false));

        List<Partido> partidos = new ArrayList<>();
        partidos.add(new Partido());
        partidos.add(new Partido());

        GenerarCuadros generador = new GeneraciónCabezaSerie();
        generador.crearPartidos(participantes, partidos);

        Assertions.assertEquals(Fase.NO_JUGADO, partidos.get(0).getFase());
        Assertions.assertEquals(Fase.NO_JUGADO, partidos.get(1).getFase());

        // Verificar que los participantes asignados a los partidos son diferentes
        Assertions.assertNotEquals(partidos.get(0).getParticipante1(), partidos.get(0).getParticipante2());
        Assertions.assertNotEquals(partidos.get(1).getParticipante1(), partidos.get(1).getParticipante2());
    }

    @Test
    public void testGeneracionCabezaSerieMayor() {
        List<Participante> participantes = new ArrayList<>();
        Participante cabezaSerie1 = new Participante("Cabeza de Serie 1", 19, true);
        cabezaSerie1.setSeed(true);
        Participante cabezaSerie2 = new Participante("Cabeza de Serie 2", 18, true);
        cabezaSerie2.setSeed(true);
        participantes.add(cabezaSerie1);
        participantes.add(new Participante("Participante 2", 44, true));
        participantes.add(new Participante("Participante 3", 16, false));
        participantes.add(cabezaSerie2);

        List<Partido> partidos = new ArrayList<>();
        partidos.add(new Partido());
        partidos.add(new Partido());

        GenerarCuadros generador = new GeneraciónCabezaSerie();
        generador.crearPartidos(participantes, partidos);

        Assertions.assertEquals(Fase.NO_JUGADO, partidos.get(0).getFase());
        Assertions.assertEquals(Fase.NO_JUGADO, partidos.get(1).getFase());

        // Verificar que los participantes asignados a los partidos son diferentes
        Assertions.assertNotEquals(partidos.get(0).getParticipante1(), partidos.get(0).getParticipante2());
        Assertions.assertNotEquals(partidos.get(1).getParticipante1(), partidos.get(1).getParticipante2());

    }

    @Test
    public void testGeneracionCabezaSerieMenor() {
        List<Participante> participantes = new ArrayList<>();
        Participante cabezaSerie1 = new Participante("Cabeza de Serie 1", 19, true);
        cabezaSerie1.setSeed(true);
        Participante cabezaSerie2 = new Participante("Cabeza de Serie 2", 18, false);
        cabezaSerie2.setSeed(true);
        participantes.add(cabezaSerie1);
        participantes.add(new Participante("Participante 2", 44, false));
        participantes.add(new Participante("Participante 3", 16, false));
        participantes.add(cabezaSerie2);

        List<Partido> partidos = new ArrayList<>();
        partidos.add(new Partido());
        partidos.add(new Partido());

        GenerarCuadros generador = new GeneraciónCabezaSerie();
        generador.crearPartidos(participantes, partidos);

        Assertions.assertEquals(Fase.NO_JUGADO, partidos.get(0).getFase());
        Assertions.assertEquals(Fase.NO_JUGADO, partidos.get(1).getFase());

        // Verificar que los participantes asignados a los partidos son diferentes
        Assertions.assertNotEquals(partidos.get(0).getParticipante1(), partidos.get(0).getParticipante2());
        Assertions.assertNotEquals(partidos.get(1).getParticipante1(), partidos.get(1).getParticipante2());

        //Verificar que no hay dos cabeza de serie en ningún partido
        Assertions.assertFalse(partidos.get(0).getParticipante1().getIsSeed() && partidos.get(0).getParticipante2().getIsSeed());
        Assertions.assertFalse(partidos.get(1).getParticipante1().getIsSeed() && partidos.get(1).getParticipante2().getIsSeed());

    }

    @Test
    public void testCrearTorneo() {
        // Crear participantes
        Participante participante1 = new Participante("Participante 1", 18, false);
        Participante participante2 = new Participante("Participante 2", 19, false);
        Participante participante3 = new Participante("Participante 3", 24, false);
        Participante cabezaDeSerie = new Participante("Cabeza de Serie", 26, true);
        Participante participante5 = new Participante("Participante 5", 18, true);
        Participante participante6 = new Participante("Participante 6", 19, false);
        Participante participante7 = new Participante("Participante 7", 18, false);
        Participante participante8 = new Participante("Participante 8", 19, false);

        // Crear instancia de ManagerTorneo
        ManagerTorneo manager = new ManagerTorneo();
        manager.crearTorneo("EJEMPLO");

        // Agregar participantes al torneo
        manager.anadirParticipantes(participante1, "EJEMPLO");
        manager.anadirParticipantes(participante2, "EJEMPLO");
        manager.anadirParticipantes(participante3, "EJEMPLO");
        manager.anadirParticipantes(cabezaDeSerie, "EJEMPLO");

        Generación a = Generación.ALEATORIO;
        manager.empezarTorneo("EJEMPLO", a);

        Partido p1;
        p1 = manager.buscarPartido(manager.buscarTorneo("EJEMPLO"), 2);
        p1.cambiarFase(null);
        p1.cambiarFase(p1.getParticipante1());

        // Verificar que se hayan generado la cantidad correcta de partidos
        Assertions.assertEquals(p1.getParticipante1(), p1.getGanador());

        // Crear otro torneo
        manager.crearTorneo("SAMU");

        // Agregar participantes al torneo
        manager.anadirParticipantes(participante5, "SAMU");
        manager.anadirParticipantes(participante6, "SAMU");
        manager.anadirParticipantes(participante3, "SAMU");
        manager.anadirParticipantes(cabezaDeSerie, "SAMU");

        Generación e = Generación.CABEZADESERIE;
        manager.empezarTorneo("SAMU", e);

        Partido p4;
        p4 = manager.buscarPartido(manager.buscarTorneo("SAMU"), 3);
        p4.cambiarFase(null);
        p4.cambiarFase(p4.getParticipante2());

        // Verificar que se hayan generado la cantidad correcta de partidos
        Assertions.assertEquals(p4.getParticipante2(), p4.getGanador());

        manager.crearTorneo("TEO");

        // Agregar participantes al torneo
        manager.anadirParticipantes(participante5, "TEO");
        manager.anadirParticipantes(participante6, "TEO");
        manager.anadirParticipantes(participante3, "TEO");
        manager.anadirParticipantes(cabezaDeSerie, "TEO");
        manager.anadirParticipantes(participante7, "TEO");
        manager.anadirParticipantes(participante8, "TEO");
        manager.anadirParticipantes(participante1, "TEO");
        manager.anadirParticipantes(participante2, "TEO");

        Generación o = Generación.CABEZADESERIE;
        manager.empezarTorneo("TEO", o);

        Partido p8;
        p8 = manager.buscarPartido(manager.buscarTorneo("TEO"), 5);
        p8.cambiarFase(null);
        p8.cambiarFase(p8.getParticipante2());

        // Verificar que se hayan generado la cantidad correcta de partidos
        Assertions.assertEquals(p8.getParticipante2(), p8.getGanador());

        manager.informacionPartidoTorneo(manager.buscarTorneo( "EJEMPLO"));

    }
}