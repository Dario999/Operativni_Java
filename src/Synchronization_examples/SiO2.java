/*
package Synchronization_examples;

import java.util.concurrent.Semaphore;

public class SiO2 {

    Semaphore si = new Semaphore(1);
    Semaphore o = new Semaphore(2);

    Semaphore siHere = new Semaphore(0);
    Semaphore oHere = new Semaphore(0);
    Semaphore ready = new Semaphore(0);

    int Counter = 0;

    public void bond(){
        Counter++;
    }

    public void proc_Si(){
        si.acquire();
        oHere.acquire(2);

        ready.release(2);
        bond();
        si.release();
    }

    public void proc_O(){
        o.acquire();
        oHere.release();
        ready.acquire();
        bond();
        o.release();
    }

    public static void main(String[] args) {



    }

}
*/
