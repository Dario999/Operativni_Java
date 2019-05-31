package JavaNetworking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class client {
    public static void main(String[] args) throws IOException {
        Socket s = new Socket("localhost",5555);

        PrintWriter pr = new PrintWriter(s.getOutputStream());
        pr.println("hello");
        pr.flush();

        InputStreamReader in = new InputStreamReader(s.getInputStream());
        BufferedReader br = new BufferedReader(in);

        String str = br.readLine();
        System.out.println("server: " + str);



    }
}
