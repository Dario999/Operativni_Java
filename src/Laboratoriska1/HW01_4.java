package Laboratoriska1;

import java.io.*;

public class HW01_4 {
    public static void main(String[] args) throws IOException {

        BufferedReader studenti = null;
        BufferedWriter dest = null;

        try {
            studenti = new BufferedReader(new FileReader("rezultati.txt"));
            dest = new BufferedWriter(new FileWriter("desinacija.txt"));

            String line;
            boolean prva = true;
            float predmet1 = 0;
            float predmet2 = 0;
            float predmet3 = 0;
            int brojStundeti = 0;
            StringBuilder sb = new StringBuilder(0);
            while((line = studenti.readLine()) != null){
                if(prva){
                    prva = false;
                    continue;
                }
                String[] student = line.split(",");
                int o1 = Integer.parseInt(student[1]);
                int o2 = Integer.parseInt(student[2]);
                int o3 = Integer.parseInt(student[3]);
                sb.append(student[0] + "\t" + student[1] + "\t" + student[2] + "\t" + student[3] + "\n");
                predmet1 += o1;
                predmet2 += o2;
                predmet3 += o3;
                brojStundeti++;
                dest.write(sb.toString());
                System.out.println(student[0] + " ima prosek: " + (o1+o2+o3)/3.0);
            }
            System.out.println("Prosek po KRS e: " + predmet1/brojStundeti);
            System.out.println("Prosek po NRS e: " + predmet2/brojStundeti);
            System.out.println("Prosek po AOK e: " + predmet3/brojStundeti);
        }finally {
            if(studenti != null){
                studenti.close();
            }
            if(dest != null){
                dest.close();
            }
        }

    }
}
