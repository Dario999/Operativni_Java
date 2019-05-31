package Group2;

import java.io.*;
import java.util.Arrays;
import java.util.List;


/*Using Java I/O, implement the following methods
in the ExamIO class:

(10 points)void copyLargeTxtFiles(String from,
                        String to, long size)

    Copies all .txt files which are larger than size (in bytes) from
    the from directory into the to directory. If the from directory
    does not exist, you should write "Does not exist" and if to does
    not exist you need to create it.

(10 points)void serializeData(String destination, List<byte[]> data)

    The list of data in data is written into the destination file,
    without delimiters (as a continuous stream of bytes). All elements
    from data have the same length (same number of bytes).

(Bonus 5 points)byte[] deserializeDataAtPosition(String source, long position, long elementLength)

    Reads and returns the data at the position position from the source file,
    which contains a large number of data, all with the same length in bytes,
    without delimiters. All data elements have the same elementLength length.
    You should not read the entire file in this method.*/


class ExamIO {

    public void copyLargeTxtFIles(String from,String to,long size) throws IOException {

        File source = new File(from);
        File dir = new File(to);

        if(!source.exists()){
            System.out.println("Does not exist");
        }

        if(!dir.exists()){
            dir.mkdirs();
        }

        List<File> list = Arrays.asList(source.listFiles());
        for(File fajl:list){
            if(fajl.isFile()) {
                if (fajl.getName().endsWith(".txt") && fajl.length() > size) {

                    FileInputStream fis = null;
                    FileOutputStream fos = null;
                    try{
                        fis = new FileInputStream(fajl);
                        File newFile = new File(dir,fajl.getName());
                        fos = new FileOutputStream(newFile);
                        int c;
                        while((c = fis.read()) != -1){
                            fos.write(c);
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }finally {
                        if(fis != null){
                            fis.close();
                        }
                        if(fos != null){
                            fos.flush();
                            fos.close();
                        }
                    }

                }
            }
        }
    }

    public void serializeData(String destination,List<byte[]> data) throws IOException {
        FileOutputStream fos = null;
        try{
            for(byte[] element: data){
                fos.write(element);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(fos != null){
                fos.flush();
                fos.close();
            }
        }
    }

    public byte[] deserializeDataAtPosition(String source,long posistion,long elementLength) throws IOException {
        RandomAccessFile rr = null;
        byte[] res = null;
        try{
            rr = new RandomAccessFile(source,"r");
            long pos = (posistion-1)*elementLength;
            rr.seek(pos);
            res = new byte[(int)elementLength];
            for(int i=0;i<elementLength;i++){
                res[i] = rr.readByte();
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(rr != null){
                rr.close();
            }
        }
        return res;
    }


    public static void main(String[] args) {

    }
}
