package Laboratoriska1;

import java.io.File;
import java.io.IOException;

public class HW01_1 {

    public static void main(String[] args) throws IOException{

        File f = new File(args[0]);
        File[] niza = f.listFiles();
        float suma = 0;
        int brojac = 0;

        for(File temp: niza){
            if(temp.isFile()){
                if(temp.getName().endsWith(".txt")){
                    suma += temp.length();
                    brojac++;
                }
            }
        }

        System.out.println((suma/1024) / brojac + "kb");
    }

}
