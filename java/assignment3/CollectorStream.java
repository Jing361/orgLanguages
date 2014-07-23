import java.util.ArrayList;

public class CollectorStream extends Stream{
  ArrayList<Object> values;

  public CollectorStream() {
    values = new ArrayList<Object>();
  }

  synchronized public void putIt(Object t){
    values.add(t);
    notify();
    try{ wait(); } catch (Exception e) {  }
  }

  synchronized public Object next() {
    if (this.getState() == Thread.State.NEW) start(); else notify();

    try { wait(); } catch (Exception e) {  }

    return values.remove(0);
  }

  public void add(Producer p){
  }
}

