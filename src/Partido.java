
public final class Partido extends Subject implements Observador {
    private int id, ronda = 0;
    private Participante participante1, participante2, ganador;
    private Partido predecesorParticipante1, predecesorParticipante2, antecesorParticipante1, antecesorParticipante2;
    private Fase fase;

    public Partido (){
    }

    public Fase getFase() {
        return fase;
    }

    public void setFase(Fase fase) {
        this.fase = fase;
    }

    public Partido getPredecesorParticipante1() {
        return predecesorParticipante1;
    }

    public void setPredecesorParticipante1(Partido predecesorParticipante1) {
        this.predecesorParticipante1 = predecesorParticipante1;
    }

    public Partido getPredecesorParticipante2() {
        return predecesorParticipante2;
    }

    public void setPredecesorParticipante2(Partido predecesorParticipante2) {
        this.predecesorParticipante2 = predecesorParticipante2;
    }

    public Partido getAntecesorParticipante1() {
        return antecesorParticipante1;
    }

    public void setAntecesorParticipante1(Partido antecesorParticipante1) {
        this.antecesorParticipante1 = antecesorParticipante1;
    }

    public Partido getAntecesorParticipante2() {
        return antecesorParticipante2;
    }

    public void setAntecesorParticipante2(Partido antecesorParticipante2) {
        this.antecesorParticipante2 = antecesorParticipante2;
    }

    public Participante getParticipante1() {
        return participante1;
    }

    public void setParticipante1(Participante participante1) {
        this.participante1 = participante1;
    }

    public Participante getParticipante2() {
        return participante2;
    }

    public void setParticipante2(Participante participante2) {
        this.participante2 = participante2;
    }

    public Participante getGanador() {
        return ganador;
    }

    public void setGanador(Participante ganador) {
        this.ganador = ganador;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRonda(int ronda) {
        this.ronda = ronda;
    }

    public int getId() {
        return id;
    }

    public int getRonda() {
        return ronda;
    }

    public void cambiarFase(Participante ganador){  //Si aún no hay ganador, se llamará a la función con null
        if((ganador == null || this.participante1.equals(ganador) || this.participante2.equals(ganador)) && (this.participante1 != null || this.participante2 != null)) {
            switch (this.fase) {
                case FINALIZADO -> System.out.println("\nNo se puede cambiar la fase de un partido finalizado.\n");
                case ESPERANDO -> this.setFase(Fase.NO_JUGADO);
                case NO_JUGADO -> this.setFase(Fase.EN_JUEGO);
                case EN_JUEGO -> {
                    this.setFase(Fase.FINALIZADO);
                    setGanador(ganador);
                    notificarObservador();
                }
            }
        }else{
            System.out.print("No se puede cambiar la fase.\n");
        }
    }

    @Override
    public void update(Subject s) {
        Partido p = (Partido) s;
        if (this.predecesorParticipante1.fase == Fase.FINALIZADO && participante1 == null) {
            this.setParticipante1(this.getPredecesorParticipante1().ganador);
        } else if (this.predecesorParticipante2.fase == Fase.FINALIZADO && participante2 == null) {
            this.setParticipante2(this.getPredecesorParticipante2().ganador);
        }
        if(this.participante1 != null && this.participante2 != null){
            this.cambiarFase(null);
        }
    }
}