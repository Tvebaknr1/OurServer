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
public class OurSocket implements StaticSubjectInterface {

    static ArrayList<ObserverInterface> observers = new ArrayList<>();
    static String ip = "localhost";
    static int portNum = 8080;
    private static ArrayList<Thread> thread = new ArrayList<>();
    static ArrayList users = new ArrayList();

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
            ServerThread t = new ServerThread(link);
            register(t);
            t.start();

            thread.add(t);

       }

    }

    public static void MSG(String str, String sender) {
        String[] temp = str.split(":");
        String[] receivers = temp[1].split(",");
        String msg = "MSGRES:"+sender+":" + temp[2];
        System.out.println(str);
        if(receivers.length>1)
        notifyObserver(msg, receivers);
        else if(receivers.length==1 && !receivers[0].equals("")){
            notifyObserver(msg, receivers[0]);
        }
        else{
            notifyObserver(msg);
        }
    }

    // @Override
    public static void register(ObserverInterface o) {
        observers.add(o);
    }

    public static void notifyObserver(String s,String user) {
        for (ObserverInterface o : observers) {
            if(o.getusername().equals(user))
            o.update(s);
        }
    }
    // @Override
    public static void notifyObserver(String s) {
        for (ObserverInterface o : observers) {
            o.update(s);
        }
    }

    public static void notifyObserver(String s, String[] users) {
        for (ObserverInterface o : observers) {
            for (String user : users) {
                if (o.getusername().equals(user)) {
                    o.update(s);
                }
            }
        }
    }

    public static void addUsers(String user) {
        users.add(user);
        String str = "CLIENTLIST:";
        for(int i = 0; i < users.size()-1; i++)
        {
            str += users.get(i) + ",";
        }
        str += users.get(users.size()-1);
        
        notifyObserver(str);
    }

    public static ArrayList getUsers() {
        return users;
    }

    public static void deleteUsers(String user) {
        users.remove(user);
        String str = "MSGRES:" + user + ":" + user + " HAS LOGGED OUT";
        notifyObserver(str);
    }

}
