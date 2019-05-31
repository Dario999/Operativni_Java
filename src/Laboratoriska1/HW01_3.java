package Laboratoriska1;

import java.io.*;

public class HW01_3 {
    public static void main(String[] args) throws IOException{

        BufferedReader izvor = null;
        BufferedWriter dest = null;

        try{
            izvor = new BufferedReader(new FileReader("izvor.txt"));
            dest = new BufferedWriter(new FileWriter("destinacija.txt"));

            String l;
            StringBuilder sb = new StringBuilder(0);
            while ((l = izvor.readLine()) != null){
                sb.append(l);
            }
            dest.write(sb.reverse().toString());
        }finally {
            if(izvor != null){
                izvor.close();
            }
            if(dest != null){
                dest.close();
            }
        }

    }
}
