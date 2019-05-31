package Laboratoriska2;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;



class ClientStarterWorkerThread extends Thread {

    private int ID;
    private DataInputStream inputStream;

    public ClientStarterWorkerThread(int clientID, DataInputStream inputStream) {
        this.ID = clientID;
        this.inputStream = inputStream;
    }

    @Override
    public void run() {
        // todo: Handle listening to messages
        String line;
        while (true) {
            try {
                line = inputStream.readUTF();
                if (line.equals("END")) {
                    break;
                }
                System.out.println("Client:" + this.ID + " received: " + line);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

public class ClientStarer {

    private int ID;
    private Socket socket;
    private DataInputStream inputStream;
    private DataOutputStream outputStream;
    //todo: init other required variables here

    ClientStarer(int id, String host, int port) throws IOException {
        this.ID = id;
        // todo: Connect to server and send client ID
        this.socket = new Socket(host, port);
        this.initializeOS();
        outputStream.writeInt(this.ID);
        outputStream.flush();
        this.listen();
        // todo: Listen for incoming messages
    }
    private void initializeOS() throws IOException {
        this.inputStream = new DataInputStream(socket.getInputStream());
        this.outputStream = new DataOutputStream(socket.getOutputStream());
    }
    // todo: Implement the sending message mechanism
    void sendMessage(int idReceiver, String message) throws IOException {
        String toSend = String.format("%s:%d", message, idReceiver);
        outputStream.writeUTF(toSend);
        outputStream.flush();
    }

    // todo: end communication - send END to server
    private void endCommunication() throws IOException {
        outputStream.writeUTF("END");
        outputStream.flush();
    }

    // todo: listen for icoming messages from the server.
    // It should start a separate thread to handle listening
    // and not block the execution
    // Should start a new ClientStarterWorkerThread
    private void listen() {
        ClientStarterWorkerThread clientStarterWorkerThread = new ClientStarterWorkerThread(this.ID, inputStream);
        clientStarterWorkerThread.start();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        //todo: Initialize and start 3 clients


        ClientStarer client1 = new ClientStarer(1, "localhost", 8996);
        ClientStarer client2 = new ClientStarer(2, "localhost", 8996);
        ClientStarer client3 = new ClientStarer(3, "localhost", 8996);
        // Simulate chat
        client1.sendMessage(2, "Hello from client 1");
        Thread.sleep(1000);
        client2.sendMessage(3, "Hello from client 2");
        Thread.sleep(1000);
        client1.sendMessage(3, "Hello from client 1");
        Thread.sleep(1000);
        client3.sendMessage(1, "Hello from client 3");
        Thread.sleep(1000);
        client3.sendMessage(2, "Hello from client 3");

        // Exit the chatroom
        client1.endCommunication();
        client2.endCommunication();
        client3.endCommunication();
    }
}


// SERVER



class TCPServer {

    private ServerSocket server;
    private HashMap<Integer, Socket> activeConnections;

    // todo: Get the required connection
    public Socket getConnection(int id) {
        return activeConnections.getOrDefault(id, null);
    }

    // todo: Add connected client to the hash map
    void addConnection(int id, Socket connection) {
        activeConnections.putIfAbsent(id, connection);
    }

    synchronized void endConnection(int id){
        activeConnections.remove(id);
    }

    //todo: Initialize server
    TCPServer(int port) throws IOException {
        server = new ServerSocket(port);
        activeConnections = new HashMap<>();
    }

    // todo: Handle server listening
    // todo: For each connection, start a separate
    // todo: thread (ServerWorkerThread) to handle the communication
    void listen() throws IOException {

        while (true) {
            Socket client = server.accept();
            ServerWorkerThread serverWorkerThread = new ServerWorkerThread(client, this);
            serverWorkerThread.start();
        }

    }

    public static void main(String[] args) throws IOException {
        // todo: Start server
        TCPServer server = new TCPServer(8996);
        server.listen();
    }
}

class ServerWorkerThread extends Thread {

    private Socket client;
    private TCPServer server;
    private DataInputStream inputStream;

    public ServerWorkerThread(Socket client, TCPServer server) {
        this.client = client;
        this.server = server;
        this.initializeOS();
    }

    private void initializeOS()  {
        try {
            inputStream = new DataInputStream(client.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            int clientId = inputStream.readInt();
            this.server.addConnection(clientId, client);

            String line;
            while (true) {
                line = inputStream.readUTF();

                if (line.equals("END")) {
                    this.server.endConnection(clientId);
                    DataOutputStream outputStream = new DataOutputStream(client.getOutputStream());
                    outputStream.writeUTF("END");
                    outputStream.flush();
                    break;
                }

                String [] split = line.split(":");
                int receiverID = Integer.parseInt(split[1]);
                Socket receiver = this.server.getConnection(receiverID);

//                if (receiver == null) {
//                    continue;
//                }

                DataOutputStream outputStream = new DataOutputStream(receiver.getOutputStream());
                outputStream.writeUTF(split[0]);
                outputStream.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

