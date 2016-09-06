/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

/**
 *
 * @author Emil
 */
public class ServerThread extends Thread implements ObserverInterface{

    private Socket s;

    public ServerThread(Socket s) {
        this.s = s;
    }

    private void handleclient(Socket s) {
        try {
            Scanner scr = new Scanner(s.getInputStream());
            PrintWriter prnt = new PrintWriter(s.getOutputStream(), true);
            String msg = "";
            prnt.println("wellcome");
            boolean loggedin = false;
            
            while (!loggedin) {
                if(msg != ""){
                    
                }
            }
            while (loggedin) {

            }
            scr.close();
            prnt.close();
            s.close();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void run() {
        handleclient(s);
    }

    @Override
    public void update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   

}
