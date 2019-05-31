package JavaThreading;

public class ThreadB1 extends Thread{
    public void run(){
        for(int i=-1;i>=-20;i--){
            System.out.println("\tB: " + i);
        }
        System.out.println("B done");
    }
}
