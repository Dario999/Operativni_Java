package Prv_Kolok_2019;

import java.io.*;
import java.net.*;
import java.util.LinkedList;

public class ServerWorkerThreadMain {

    public static class ServerWorkerThread extends Thread {
        Socket socket;
        BufferedWriter bufferedWriter;


        public ServerWorkerThread(Socket socket, BufferedWriter bufferedWriter) {
            super();
            this.socket = socket;
            this.bufferedWriter = bufferedWriter;
        }

        public void run(){
            try {
                DataInputStream reader = new DataInputStream(socket.getInputStream());
                bufferedWriter.write(socket.getLocalAddress() + " " + socket.getPort() + " " + reader.readInt()+"\n");
                bufferedWriter.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    public static class TcpClient extends Thread{
        Socket s;
        static int  sum = 0;
        String folder;

        public TcpClient(String folder){
            try {
                s = new Socket(InetAddress.getByName("localhost"), 6668);
                this.folder = folder;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void recursive (File folder){
            if(!folder.isDirectory()) {
                System.err.println("Nevaliden folder!");
                return;
            }
            try {
                for (File file : folder.listFiles()) {
                    if (file.isDirectory())
                        recursive(file);
                    else if (file.getName().endsWith(".jpg"))
                        sum += file.length();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void run(){
            recursive(new File(folder));
            DataOutputStream writer;
            try {
                writer = new DataOutputStream(s.getOutputStream());
                writer.writeInt(sum);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static class TCPServer extends Thread{
        ServerSocket server;
        LinkedList<ServerWorkerThread> connections;
        BufferedWriter writer;

        public TCPServer() throws IOException {
            server = new ServerSocket(6668);
        }

        public void run() {
            try {
                writer = new BufferedWriter(new FileWriter("./zapisuvaj.txt"));
                while(true){
                    Socket s = server.accept();
                    ServerWorkerThread sc = new ServerWorkerThread(s, writer);
                    sc.start();
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) throws IOException, InterruptedException {

        TCPServer server = new TCPServer();
        server.start();
        TcpClient client = new TcpClient(".");
        TcpClient client2 = new TcpClient("./src");
        client.start();
        client2.start();
        server.join();

    }


}
