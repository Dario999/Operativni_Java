package Kolok_aud;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

public class client extends Thread{

    private Socket socket = null;
    private String sourceDir;

    public client(String dir,String dest,int port) throws IOException {
        socket = new Socket(InetAddress.getByName("localhost"),port);
        sourceDir = dir;
    }

    public void sendFiles() throws IOException {
        File f = new File(sourceDir);
        List<File> list = Arrays.asList(f.listFiles());
        for(File tmp:list){
            if(tmp.isFile()){
                sendFile(tmp);
            }
        }
        socket.close();
    }

    public void sendFile(File file){
        BufferedReader br = null;
        PrintWriter writer = null;
        String line = null;
        try{
            writer = new PrintWriter(socket.getOutputStream());
            br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            writer.printf("###%s### ",file.getName());
            writer.flush();
            while ((line = br.readLine()) != null){
                writer.println(line);
                writer.flush();
            }
            writer.println("!!!END!!!");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run(){
        try{
            sendFiles();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {

        client client1 = new client("E:\\Test\\from1" , "E:\\Test\\dest" , 9876);
        //client client2 = new client("E:\\Test\\from1" , "E:\\Test\\dest" , 9876);

        client1.start();
        //client2.start();

    }

}
