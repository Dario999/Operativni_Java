package Group1;

import java.io.*;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/*Using Java I/O, implement the following
methods of the ExamIO class:

(10 points) moveWritableTxtFiles(String from, String to)

    Moves all files with the .txt extension which have writing
    permissions, from the from directory, to the to directory.
    If the from directory does not exist you should write "Does not exist",
    and if the to directory does not exist you need to create it.

(10 points) void deserializeData(String source, List<byte[]> data, long elementLength)

    Reads the content of the source file, which contains a large amount of
    data, all in the same length in bytes, without a delimiter. Each of the
    data elements has a length of elementLength. The data read should be
    written in the data list, using data.add(readElement).

(Bonus 5 points) void invertLargeFile(String source, String destination)

    The content of the source file is written in a reverse order (char by char)
    into the destination file. The source file content is too large and cannot
    be fitted into memory.*/

class ExamIO {

    public void moveWritableTxtFiles(String from,String to) throws IOException {

        File source = new File(from);
        File dir = new File(to);

        if(!source.exists()){
            System.out.println("Ne postoi");
            return;
        }

        if(!dir.exists()){
            dir.mkdirs();
        }

        List<File> files = Arrays.asList(source.listFiles());
        for(File file:files){
            if(file.getName().endsWith(".txt") && file.canWrite()){
                File newFile = new File(dir,file.getName());
                newFile.createNewFile();
                file.renameTo(newFile);
            }
        }

    }

    public void deserializeData(String source, List<byte[]> data,long elementLength) throws IOException {

        FileInputStream fis = null;

        try {
            fis = new FileInputStream(source);

            byte[] element = new byte[(int)elementLength];
            int b ,read = 0;
            while ((b = fis.read()) != -1){
                element[read] = (byte)b;
                read++;
                if(read == elementLength){
                    data.add(element);
                    read = 0;
                    element = new byte[(int) elementLength];
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(fis != null){
                fis.close();
            }
        }

    }


    public void invertLargeFile(String source,String destination) throws IOException {

        RandomAccessFile rr = null;
        RandomAccessFile rw = null;

        try{
            rr = new RandomAccessFile(source,"r");
            rw = new RandomAccessFile(source,"rw");

            long pos = rr.length()-2;
            while(pos >= 0){
                char c = rr.readChar();
                rw.seek(pos);
                rw.write(c);
                pos -= 2;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(rr != null){
                rr.close();
            }
            if(rw != null){
                rw.close();
            }
        }


    }

    public static void main(String[] args) throws IOException{

        ExamIO exam = new ExamIO();



    }

}
