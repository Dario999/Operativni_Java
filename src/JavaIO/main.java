package JavaIO;

import java.io.*;
import java.nio.Buffer;
import java.util.*;

class DirFilter implements FilenameFilter{
    String afn;

    public DirFilter(String afn){
        this.afn = afn;
    }

    public boolean accept(File dir,String name){
        String f = new File(name).getName();
        return f.indexOf(afn) != -1;
    }
}

public class main {

    public static void copyStream(InputStream in ,OutputStream out) throws IOException{
        try {
            int c;
            while ((c = in.read()) != -1) {
                out.write(c);
            }
        }finally {
            if(in != null){
                in.close();
            }
            if(out != null){
                out.close();
            }
        }
    }

    public static void correctReading(InputStream in) throws IOException{
        try{
            byte[] buffer = new byte[100];
            int readLen = 0;
            int leftToBeRead = 100;
            int offset = 0;
            while ((readLen = in.read(buffer,offset,leftToBeRead)) != -1){
                offset += readLen;
                leftToBeRead -= readLen;
            }
            //doSomethingWithReadData(buffer,offset);
        }finally {
            if(in != null){
                in.close();
            }
        }
    }

    public static String readTextFile(String path)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(
                new FileInputStream(path),"UTF-8"));

        String line = null;
        StringBuilder sb = new StringBuilder();

        while ((line = br.readLine()) != null){
            sb.append(line).append("\n");
        }
        br.close();
        return sb.toString();

    }

    public static void stdinRead() throws IOException{
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        String s;
        System.out.println("Enter a line:");
        while((s = stdin.readLine()) != null && s.length() != 0){
            System.out.println(s);
        }
    }

    public static void writeTextFile(String path,String text,boolean appen)
        throws IOException{
        BufferedWriter br = new BufferedWriter(new FileWriter(path,appen));
        br.write(text);
        br.close();
    }

    public static void memorySafeTextFileCopy(String from,String to)
        throws IOException{
        BufferedReader br = new BufferedReader(new FileReader(from));
        BufferedWriter bw = new BufferedWriter(new FileWriter(to));

        String line = null;

        while ((line = br.readLine()) != null){
            bw.write(line + "\n");
        }
        br.close();
        bw.close();
    }

    public static void listFile(String absolutePath,String prefix){
        File file = new File(absolutePath);

        if(file.exists()){
            File[] subfiles = file.listFiles();
            for(File f:subfiles){
                System.out.println(prefix + getPermission(f)
                    + "\t" + f.getName());

                if(f.isDirectory()){
                    listFile(f.getAbsolutePath() , prefix + "\t");
                }
            }
        }
    }

    public static String getPermission(File f){
        return String.format("%s%s%s",f.canRead() ? "r" : "-",
                f.canWrite() ? "w" : "-" , f.canExecute() ? "x" : "-");
    }


    public static void main(String[] args) throws IOException{

        File path = new File(".");
        String[] list;
        if(args.length == 0){
            list = path.list();
        }else {
            list = path.list(new DirFilter(args[0]));
        }
        for (int i=0;i<list.length;i++)
            System.out.println(list[i]);
    }
}
