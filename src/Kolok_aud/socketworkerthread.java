package Kolok_aud;

import java.io.*;
import java.net.Socket;
import java.util.Map;

public class socketworkerthread extends Thread {

    private Socket socket = null;
    private String destDir;

    public socketworkerthread(Socket socket,Map<Integer,Socket> mapa,String dir){
        this.socket = socket;
        this.destDir = dir;
        System.out.println("New connection from: " + socket.getInetAddress().getHostAddress()+ "port:"+socket.getPort());
        mapa.put(socket.getLocalPort(),socket);
    }

    @Override
    public void run(){
        BufferedReader br = null;
        PrintWriter writer = null;
        try{
            String line = null;
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while((line = br.readLine()) != null){
                System.out.println(line);
                if(line.startsWith("###")){
                    writer = new PrintWriter(new File(this.destDir + "/" + line.replace("#","")));
                }else if(line.equals("!!!END!!!")){
                    writer.flush();
                    writer.close();
                }else {
                    writer.println(line);
                    writer.flush();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
