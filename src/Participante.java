public class Participante {
    private String name;
    private int age;
    private boolean isSeed;

    public Participante(String name, int age, boolean isSeed){
        this.name = name;
        this.age = age;
        this.isSeed = isSeed;
    }
    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }
    public boolean getIsSeed() {
        return isSeed;
    }
    public void setSeed(boolean isSeed) {
        this.isSeed = isSeed;
    }
}