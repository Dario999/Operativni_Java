package Laboratoriska1;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

class TCPClient{

    private Socket socket;
    private Scanner scanner;
    private TCPClient(InetAddress serverAddress,int serverPort) throws Exception{
        this.socket = new Socket(serverAddress,serverPort);
        this.scanner = new Scanner(System.in);
    }

    private void start() throws IOException{
        String input;
        while(true){
            input = scanner.nextLine();
            PrintWriter out = new PrintWriter(this.socket.getOutputStream(),true);
            out.println(input);
            out.flush();
        }
    }

    public static void main(String[] args) throws Exception {
        TCPClient client = new TCPClient(InetAddress.getByName("localhost"),9876);
        System.out.println("\r\nConnected to Server: " + client.socket.getInetAddress());
        client.start();
    }

}
class TCPServer {

    private ServerSocket server;

    public TCPServer() throws Exception{
        this.server = new ServerSocket(9876);
    }

    private void listen() throws Exception{
        String data = null;
        Socket client = this.server.accept();
        String clientAddress = client.getInetAddress().getHostAddress();
        System.out.println("\r\nNew Connection from " + clientAddress);

        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));

        while ((data = in.readLine()) != null){
            System.out.println("\r\nMessage from " + clientAddress + ": " + data);
        }
    }

    public static void main(String[] args) throws Exception {
        TCPServer app = new TCPServer();
        System.out.println("\r\nRunning Server: " +
                "Host=" + app.server.getInetAddress() +
                " Port=" + app.server.getLocalPort());
        app.listen();
    }


}

