import java.util.ArrayList;
import java.util.List;

public class Subject {
    private List<Observador> observers = new ArrayList<>();
    public void attach(Observador o){ observers.add(o);}
    public void detach(Observador o){ observers.remove(o);}
    public void notifyObservers(){
        for(Observador o : observers){
            o.update(this);
        }
    }
}
