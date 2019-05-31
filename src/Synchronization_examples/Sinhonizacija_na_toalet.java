/*
package Synchronization_examples;

import java.util.concurrent.Semaphore;

public class Sinhonizacija_na_toalet {

    class Toalet{
        int counter;

        public Toalet(){
            this.counter = 0;
        }

        public void vlezi(){
            counter++;
        }
        public void izlezi(){
            counter--;
        }
    }

    Toalet wc = new Toalet();

    Semaphore toalet = new Semaphore(1);
    final Object mLock = new Object();
    final Object zLock = new Object();
    int maziVnatre = 0, zeniVnater = 0;

    void maz_vleguva(){
        synchronized (mLock){
            if(maziVnatre == 0){
                toalet.acquire();  //se zafakja
            }
            maziVnatre++;
            wc.vlezi();
        }
    }

    void maz_izleguva(){
        synchronized (mLock){
            wc.izlezi();
            maziVnatre--;
            if(maziVnatre == 0){
                toalet.release();  //se osloboduva
            }
        }
    }

    void zena_vleguva(){
        synchronized (zLock){
            if(zeniVnater == 0){
                toalet.acquire();
            }
            zeniVnater++;
            wc.vlezi();
        }
    }

    void zena_izleguva(){
        synchronized (zLock){
            wc.izlezi();
            zeniVnater--;
            if(zeniVnater == 0){
                toalet.release();
            }
        }
    }

}
*/
