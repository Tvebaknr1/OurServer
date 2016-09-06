/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author Emil
 */
public class socketmain implements StaticSubjectInterface{
    static ArrayList<ObserverInterface> observers = new ArrayList<>();
    static String ip = "localhost";
    static int portNum = 8080;
    private static ArrayList<Thread> thread = new ArrayList<>();

    public static void main(String[] args) throws IOException, InterruptedException {
        if (args.length == 2) {
            ip = args[0];
            portNum = Integer.parseInt(args[1]);

        }
        ArrayList socketArray = new ArrayList();
        ServerSocket ss = new ServerSocket();
            ss.bind(new InetSocketAddress(ip, portNum));
            


           
        while (true) {
             Socket link = ss.accept();
            socketArray.add(link);

            System.out.println("new client connection");
            SeverThread t = new SeverThread(link);
            t.start();
            
            thread.add(t);
           
        }

    }

   // @Override
    public static void register(ObserverInterface o) {
        observers.add(o);
    }

   // @Override
    public static void notifyObserver() {
        for(ObserverInterface o: observers)
        {
            o.update();
        }
    }

}
