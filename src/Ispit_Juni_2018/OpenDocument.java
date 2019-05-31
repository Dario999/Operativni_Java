package Ispit_Juni_2018;

import java.io.*;

/*(25 поени) Потребно е да направите апликација која пребарува Open Document датотеки кои се многу големи.
За таа потреба имплементирајте метод findOdf(String in, String out) кој ќе го изминува рекурзивно директориумот
претставен преку “in”. Доколку наиде на .odt или .ods кои се поголеми од 1 мегабајт, тогаш треба да ги ископира
во “out”\brisenje, а доколку наиде на .ods или .odt кои се помали или еднакви на 1 мегабајти, тогаш треба да додаде
 линија во “out”\mali.txt од типот “Datotekata ima _in_kilobytes>”. На местото <filename> ставете го името на
 датотеката и на местото на <site_in_kilobytes> ставете ја големината на датотеката во килобајти. Внимавајте
  дека може да има многу вакви документи во “in”.*/

public class OpenDocument {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String in = br.readLine();
        String out = br.readLine();


        br.close();
        findOf(in,out);
    }

    public static void findOf(String in,String out) throws IOException{

        File izvor = new File(in);
        File dest = new File(out);
        File mali = new File(dest.getAbsolutePath() + "\\" + "mali.txt");

        if(!izvor.exists())
            return;
        if(!dest.exists())
            dest.mkdirs();

        File[] lista = izvor.listFiles();
        for(File f:lista){
            if(f.isDirectory())
                findOf(f.getAbsolutePath(),dest.getAbsolutePath());

            if(f.isFile() && (f.getName().endsWith(".ods") || f.getName().endsWith(".odt"))){
                if(f.length() > 1000000){
                    File brisenje = new File(dest.getAbsolutePath() + "\\" + "brisenje");
                    if(!brisenje.exists())
                        brisenje.mkdirs();

                    izvor.renameTo(new File(brisenje.getAbsolutePath() + "\\" + f.getAbsolutePath()));
                }else{
                    FileWriter fw = new FileWriter(mali,true);
                    fw.write("Datotekata " + f.getName() + " ima " + f.length()/1024 + " KB");
                }
            }
        }

    }

}
