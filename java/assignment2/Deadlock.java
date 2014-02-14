import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.locks.*;

class Monitor {
  String name;

  public Monitor (String name) { this.name = name; }

  public String getName() { return this.name; }

  public synchronized void ping (Monitor p) {
    p.release();
    try{
      wait();
    } catch (Exception e) {}
    System.out.println(this.name + " (ping): pinging " + p.getName());
    p.confirm(this);
    System.out.println(this.name + " (ping): got confirmation");
    p.release();
  }

  public synchronized void confirm (Monitor p) {
    System.out.println(this.name + " (confirm): confirm to " + p.getName());
  }

  public synchronized void release() {
    notify();
  }

}

class Runner extends Thread {
  Monitor m1, m2;

  public Runner (Monitor m1, Monitor m2) {
    this.m1 = m1;
    this.m2 = m2;
  }

  public void run() { m1.ping(m2); }
}

public class Deadlock {
  public static void main (String args[]) {
    int i = 1;
    Container stuff = new Container();

    System.out.println("Starting..." + (i++));
    Monitor a = new Monitor("Girl");
    Monitor b = new Monitor("Boy ");
    (new Runner(a, b)).start();
    (new Runner(b, a)).start();
  }
}
