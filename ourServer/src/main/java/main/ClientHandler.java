/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.PrintWriter;
import java.net.InetAddress;
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
    Scanner input;
    PrintWriter output;
    private InetAddress serverAddress;
    private ClientGUI gui;
    MessageReceiver msgRec;

    public ClientHandler(String ip, String port)
    {
        this.ip = ip;
        this.port = port;
    }

    @Override
    public void run()
    {
        try
        {
            serverAddress = InetAddress.getByName(ip);

            s = new Socket(serverAddress, Integer.parseInt(port));
            input = new Scanner(s.getInputStream());
            output = new PrintWriter(s.getOutputStream(), true);
            msgRec = new MessageReceiver(input);
            msgRec.register(this);
            msgRec.start();
        } catch (Exception ex)
        {
            Logger.getLogger(Log.LOG_NAME).log(Level.SEVERE, null, ex);

        }
    }


    public void addUser(String username)
    {
        this.username = username;
        String msg = "LOGIN:" + username;
        output.println(msg);
    }

    public void writeMessage(String message, String[] users)
    {
        String msg = "MSG:";

        for (int i = 0; i < users.length - 1; i++)
        {
            msg += users[i] + ",";
        }
        if (users.length > 0)
        {
            msg += users[users.length - 1];
        }
        msg += ":" + message;

        output.println(msg);
    }

    public void logout()
    {
        output.println("LOGOUT:");
    }

    @Override
    public String getusername()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(String s)
    {
        if (s.startsWith("MSGRES:"))
        {
            gui.receiveMessage(s);
        } else if (s.startsWith("CLIENTLIST:"))
        {
            gui.updateUserList(s);
        }

    }

    public void register(ClientGUI gui)
    {
        this.gui = gui;

    }

}
