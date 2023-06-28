public enum Fase {
    ESPERANDO(0), NO_JUGADO(1), EN_JUEGO(2), FINALIZADO(3);

    private final int fase;
    public int getFase(){return this.fase;}
    Fase(int fase){this.fase = fase;}
}
