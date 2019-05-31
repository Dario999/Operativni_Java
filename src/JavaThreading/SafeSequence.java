package JavaThreading;

import java.util.concurrent.SynchronousQueue;

public class SafeSequence {
    private int value;

    public SafeSequence(int value){
        this.value = value;
    }

    public void pecati(){
        int brojac = 0;
        while(brojac < 100){
            System.out.println(this.getNext());
            this.getNext();
            brojac++;
        }
    }

    public int getNext(){
        synchronized (this) {
            return value++;
        }
    }
}
