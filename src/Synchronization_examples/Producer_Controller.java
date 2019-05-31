package Synchronization_examples;

import java.util.concurrent.Semaphore;

public class Producer_Controller {

    static Semaphore accessBuffer;
    static Semaphore canCheck;
    static Semaphore lock;
    static int numChecks;

    public static void init(){
        accessBuffer = new Semaphore(1);
        canCheck = new Semaphore(10);
        lock = new Semaphore(1);
        numChecks = 0;
    }

    public static void main(String[] args){

    }
}
