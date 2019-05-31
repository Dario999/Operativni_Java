package JavaNetworking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.Buffer;
import java.util.Objects;

public class server {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(5555);
        Socket s = ss.accept();

        System.out.println("client connected");

        InputStreamReader in = new InputStreamReader(s.getInputStream());
        BufferedReader br = new BufferedReader(in);

        String str = br.readLine();
        System.out.println("client: " + str);

        PrintWriter pr = new PrintWriter(s.getOutputStream());
        pr.println("hello");
        pr.flush();
    }
}
