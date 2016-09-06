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
public class SeverThread extends Thread implements ObserverInterface{

    private Socket s;

    Scanner scr;
    PrintWriter prnt;
    public SeverThread(Socket s) {
        this.s = s;
        try{
        scr =   new Scanner(s.getInputStream());
        prnt = new PrintWriter(s.getOutputStream(), true);
        }catch(Exception ex)
        {
            
        }
    }
 
            
    private void handleclient(Socket s) {
        try {
           
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
    public void update(String s) {
        String[] StringArray = s.split(":");
        if(StringArray[0].equals("CLIENTLIST"))
        {
             String[] brugere = StringArray[1].split(",");
            prnt.print("Disse brugere er online:" );
            for(String bruger : brugere)
            {
                prnt.print(" " + bruger);
            }
            prnt.println();
        }
        else if(StringArray[0].equals("MSGRES"))
        {
            prnt.println(StringArray[1] + " says: " + StringArray[2]);
        }
        else
        {
         //tilføj fejæl her   
        }
    }

   

}
