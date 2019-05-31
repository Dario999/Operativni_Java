package JavaThreading;

public class ThreadA1 extends Thread{
    public void run(){
        for(int i=1;i<=20;i++){
            System.out.println("A: " + i);
        }
        System.out.println("A done");
    }

}
