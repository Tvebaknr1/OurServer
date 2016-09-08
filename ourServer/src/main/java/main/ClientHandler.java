/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ehn19
 */
public class ClientHandler extends Thread implements ObserverInterface
{

    private Socket s;
    private String username;
    String ip, port;
    Scanner scr;
    PrintWriter prnt;

    public ClientHandler(String ip, String port)
    {
        this.ip = ip;
        this.port = port;
    }

    @Override
    public void run()
    {
        ServerSocket ss;
        try
        {
            ss = new ServerSocket();
            int portInt = Integer.parseInt(port);
            ss.bind(new InetSocketAddress(ip, portInt));
            s = ss.accept();

            System.out.println("Has connected to server");

        } catch (IOException ex)
        {
            Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);

        }
        try
        {
            scr = new Scanner(s.getInputStream());
            prnt = new PrintWriter(s.getOutputStream(), true);
        } catch (Exception ex)
        {

        }
    }
    

    public void addUser(String username)
    {
        this.username = username;
        String msg = "LOGIN:" + username;
        prnt.println(msg);
    }
    
    public void writeMessage(String message, String[] users)
    {
        String msg = "MSG:";
        
        for (int i = 0; i < users.length-1; i++)
        {
            msg += users[i] + ",";
        }
        msg += users[users.length-1];
        msg += ":" + message;
        
        prnt.println(msg);
    }
    
    public void logout()
    {
        prnt.println("LOGOUT:");
    }

    @Override
    public String getusername()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(String s
    )
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
