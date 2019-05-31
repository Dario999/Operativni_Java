package Laboratoriska1;

import java.io.*;
import java.util.*;

public class HW01_2 {

    public static void main(String[] args) throws IOException{

        FileInputStream vlez = null;
        FileOutputStream izlez = null;

        try{
            vlez = new FileInputStream("izvor.txt");
            izlez = new FileOutputStream("destinacija.txt");

            Stack<Integer> stek = new Stack<>();
            int c;
            while((c = vlez.read()) != -1){
                stek.push(c);
            }
            while(!stek.isEmpty()){
                izlez.write(stek.pop());
            }
        }finally {
            if(vlez != null){
                vlez.close();
            }
            if(izlez != null){
                izlez.close();
            }
        }

    }

}
