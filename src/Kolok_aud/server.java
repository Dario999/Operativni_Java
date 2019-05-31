package Kolok_aud;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class server {

    public static ServerSocket socket = null;
    public static String dir = null;
    public static Map<Integer,Socket> sockets;

    public server(int port,String dir) throws IOException {
        socket = new ServerSocket(port);
        this.dir = dir;
        sockets = new HashMap<>();
    }

    public void start() throws IOException {
        while (true){
            socketworkerthread client = new socketworkerthread(socket.accept(),sockets,dir);
            client.start();
        }
    }



    public static void main(String[] args) throws IOException {
        server ser = new server(9876,"E:\\Test\\dest");
        ser.start();
    }

}
