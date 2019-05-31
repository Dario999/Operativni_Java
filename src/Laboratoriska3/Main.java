package Laboratoriska3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

class client {
    public static void main(String[] args) throws IOException {
        Socket s = new Socket("localhost",5555);
        Scanner input = new Scanner(System.in);

        InputStreamReader in = new InputStreamReader(s.getInputStream());
        BufferedReader br = new BufferedReader(in);

        String str = br.readLine();
        String str1 = br.readLine();
        System.out.println("Kandidati za glasanje: ");
        System.out.println(str);
        System.out.println(str1);

        System.out.println("Vnesi go brojot na kandidatot za koj ke glasash: ");

        PrintWriter pr = new PrintWriter(s.getOutputStream());
        int broj = input.nextInt();
        pr.println(broj);
        pr.flush();

    }
}


class server {

    static int counter1 = 0;
    static int counter2 = 0;

    public static int getCounter1(){
        return counter1;
    }

    public static int getCounter2(){
        return counter2;
    }

    public static void main(String[] args) throws IOException {

        String kandidat1 = "Petar Kostadinov";
        String kandidat2 = "Ilija Petkovski";


        ServerSocket ss = new ServerSocket(5555);
        Socket s = ss.accept();

        System.out.println("Glasac najaven.");

        PrintWriter pr = new PrintWriter(s.getOutputStream());
        pr.println("Petar Kostadinov -> 1");
        pr.println("Ilija Petkovski -> 2");
        pr.flush();

        InputStreamReader in = new InputStreamReader(s.getInputStream());
        BufferedReader br = new BufferedReader(in);

        int broj = Integer.parseInt(br.readLine());
        if(broj == 1){
            counter1++;
            System.out.println("Glasavte za kandidatot " + kandidat1 + " so reden broj: " + broj);
            System.out.println("Vkupniot broj na glasovi za kandidator " + kandidat1 + " e: " + getCounter1());
        }else if(broj == 2){
            counter2++;
            System.out.println("Glasavte za kandidatot " + kandidat2 + " so reden broj: " + broj);
            System.out.println("Vkupniot broj na glasovi za kandidator " + kandidat2 + " e: " + getCounter2());
        }else{
            System.out.println("Nevazecko livce");
        }


    }
}

