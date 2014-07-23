public class Notifier extends Stream {
   IntObject value;
   TCFrame f;

   public Notifier (TCFrame f) { this.f = f; }
   public void putValue (IntObject v) { value = v; }
   public void run () {  f.area.append(value.number+"\n");  }
}
