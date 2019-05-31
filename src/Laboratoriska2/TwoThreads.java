package Laboratoriska2;

public class TwoThreads {

    public static class Thread1 extends Thread {
        public void run() {
            System.out.println("A");
            System.out.println("B");
        }
    }

    public static class Thread2 extends Thread {
        public void run() {
            System.out.println("1");
            System.out.println("2");
        }
    }

    public static class ThreadAB implements Runnable{

        String A,B;

        public ThreadAB(String a,String b){
            this.A = a;
            this.B = b;
        }

        @Override
        public void run(){
            System.out.println(A);
            System.out.println(B);
        }
    }

    public static void main(String[] args) {
        /*new Thread1().start();
        new Thread2().start();*/
        Thread a = new Thread(new ThreadAB("A","B"));
        Thread b = new Thread(new ThreadAB("1","2"));
        a.start();
        b.start();
    }

}
