/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import javax.net.ssl.SSLServerSocket;

/**
 *
 * @author Hung Tun
 */
public class Server {
    private int port;
    private List<PrintStream> clients;
    private ServerSocket server;

    public Server(int port) {
        this.port = port;
        this.clients = new ArrayList<PrintStream>();
    }
    public void run() throws IOException{
        server = new ServerSocket(port);
        while(true){
            Socket client = server.accept();
            System.out.println("Kết nối với client: " + client.getInetAddress().getHostAddress());
            this.clients.add(new PrintStream(client.getOutputStream()));
            new Thread(new ClientHandler(this, client.getInputStream())).start();
        }
    }
    void broadcastMessages(String msg){
        for(PrintStream client : this.clients){
            client.println(msg);
        }
    }
    public static void main(String[] args) throws IOException {
        new Server(6789).run();
    }

}
