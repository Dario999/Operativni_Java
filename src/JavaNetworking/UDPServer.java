package JavaNetworking;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDPServer {
    private DatagramSocket socket;
    private byte[] buf = new byte[256];

    public UDPServer(){
        try {
            //init socket whick listens on port 4445
            socket = new DatagramSocket(4445);
        }catch (SocketException e){
            e.printStackTrace();
        }
    }

    public void run(){
        while(true){
            //init empty Datagram to wait for a new message
            DatagramPacket packet = new DatagramPacket(buf,buf.length);
            try{
                //blocks until new datagram packet is received
                socket.receive(packet);
                String received = new String(packet.getData(),0,packet.getLength());
                System.out.println("RECEIVED: " + received);
                //get client IP address
                InetAddress address = packet.getAddress();
                //get client port
                int port = packet.getPort();
                //create new datagram packet
                packet = new DatagramPacket(buf,buf.length,address,port);
                //echos the created new packet as a response, back to the client
                socket.send(packet);
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
