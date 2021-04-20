/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.InputStream;
import java.util.Scanner;

/**
 *
 * @author Hung Tun
 */
public class ClientHandler implements Runnable{
   private Server server;
   private InputStream client;

    public ClientHandler(Server server, InputStream client) {
        this.server = server;
        this.client = client;
    }

    @Override
    public void run() {
        String message;
        Scanner sc = new Scanner(this.client);
        while(sc.hasNextLine()){
            message = sc.nextLine();
            server.broadcastMessages(message);
        }
        sc.close();
    }
   
    
}
