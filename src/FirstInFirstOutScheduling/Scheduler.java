package FirstInFirstOutScheduling;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Scheduler extends Thread{
    public static Random random = new Random();
    static List<Process> scheduled = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        // TODO: create 100 Process threads and register them
        for(int i=0;i<100;i++)
            register(new Process());
        // TODO: create Scheduler and start its background execution
        Scheduler s = new Scheduler();
        // TODO: Wait for 20.000 ms for the Scheduler-ot to finish
        s.start();
        // TODO: Print out the termination status
    }

    public static void register(Process process) {
        scheduled.add(process);
    }

    public Process next() {
        if (!scheduled.isEmpty()) {
            return scheduled.remove(0);
        }
        return null;
    }

    public void run() {
        try {
            while (!scheduled.isEmpty()) {
                Thread.sleep(100);
                System.out.print(".");

                Process p = next();
                p.execute();
                // TODO: obtain the next process
                Thread.sleep(700);
                // TODO: invoke its execute() method
                if(p.isAlive()){
                    p.interrupt();
                    System.out.println("Terminated proces");
                }else {
                    System.out.println("Process finished");
                }
                // TODO: wait until this process's background execution is completed

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Done scheduling!");
    }
}



class Process extends Thread{

    public Integer duration;

    public Process() throws InterruptedException {
        this.duration = Scheduler.random.nextInt(1000);
    }


    public void execute() {
        System.out.println("Executing[" + this + "]: " + duration);
        // TODO: start the background execution
        start();
    }

    @Override
    public void run(){
        try{
            Thread.sleep(this.duration);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}