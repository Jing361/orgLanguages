public class Subscriber {
   int number;
   double stock_value;
   Producer producer;
   
   public Subscriber (int n, double v, Producer p) { 
      number = n; 
      stock_value = v;
      producer = p;
   }
}

