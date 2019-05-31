package June_exam_session;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

class main_Class {

    public static void manage(String in,String out) throws IOException {

        File from = new File(in);
        File to = new File(out);

        if(!from.exists()){
            System.out.println("ne postoi");
        }
        if(to.exists()){
            List<File> lista = Arrays.asList(to.listFiles());
            for(File f:lista){
                System.out.println("Deleting: " + f.getAbsolutePath());
                f.delete();
            }
        }else {
            to.mkdirs();
        }

        List<File> list = Arrays.asList(from.listFiles());
        for(File file:list){
            if(file.isFile()){
                if(file.getName().endsWith(".dat") && file.canWrite() && !file.isHidden()){
                    File newFile = new File(to,file.getName());
                    newFile.createNewFile();
                    System.out.println("pomestuvam " + file.getAbsolutePath());
                }
                if(file.getName().endsWith(".dat") && !file.canWrite()){
                    System.out.println("dopisuvam " + file.getAbsolutePath());

                    FileInputStream fis = null;
                    FileOutputStream fos = null;

                    try{
                        fis = new FileInputStream(file);
                        File folder = new File(from,"resources");
                        if(!folder.isDirectory()){
                            folder.mkdirs();
                        }
                        File newFile = new File(folder,"writable-content.txt");
                        fos = new FileOutputStream(newFile);
                        if(!newFile.exists()){
                            newFile.createNewFile();
                        }
                        int c;
                        while((c = fis.read()) != -1){
                            fos.write(c);
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }finally {
                        if(fis!= null){
                            fis.close();
                        }
                        if (fos != null) {
                            fos.flush();
                            fos.close();
                        }
                    }
                    //dodadi na kraj od datotekata writable-content vo resources imenikot
                }
                if(file.getName().endsWith(".dat") && file.isHidden()){
                    System.out.println("zbunet sum");
                    file.delete();
                }
            }
        }

    }

    public static void main(String[] args) throws IOException {

        String from = "E:\\Test\\from";
        String to = "E:\\Test\\to";

        manage(from,to);

    }
}
