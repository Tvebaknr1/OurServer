package main;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author Emil
 */
public class ServerThread extends Thread implements ObserverInterface
{

    private Socket s;
    private String username;

    Scanner scr;
    PrintWriter prnt;

    public ServerThread(Socket s)
    {

        this.s = s;
        try
        {
            scr = new Scanner(s.getInputStream());
            prnt = new PrintWriter(s.getOutputStream(), true);
        } catch (Exception ex)
        {

        }
    }

    private void handleclient(Socket s)
    {
        try
        {
            String msg = "";
            prnt.println("Welcome");
            boolean loggedIn = false;

            while (!loggedIn)
            {
                msg = scr.nextLine();

                if (msg != "" && msg.startsWith("LOGIN:"))
                {
                    String[] string;
                    string = msg.split(":");
                    OurSocket.addUsers(username = string[1]);                    
                    loggedIn = true;
                }
            }
            while (loggedIn)
            {
                msg = scr.nextLine();
                if (msg != "" && msg.startsWith("LOGOUT:"))
                {
                    prnt.println("Du logger ud");
                    OurSocket.deleteUsers(username);
                    loggedIn = false;


                }
                else if(msg!="" && msg.startsWith("MSG:")){
                    if(msg.split(":").length==3){
                        OurSocket.MSG(msg);
                    }
                }

                 else if(msg.contains(":"))

                {
                    String[] message = new String[2];
                    message[0] = "MSG";
                    message[1] = msg;
                    String finalMsg = message[0] + ":" + message[1];
                }
            }
            scr.close();
            prnt.close();
            s.close();
        } catch (IOException ex)
        {
            System.out.println(ex);
        }
    }

    @Override
    public void run()
    {
        handleclient(s);
    }

    @Override
    public void update(String s)
    {
        String[] StringArray = s.split(":");
        if (StringArray[0].equals("CLIENTLIST"))
        {
            /*
            String[] brugere = StringArray[1].split(",");
            prnt.print("Disse brugere er online:");
            for (String bruger : brugere)
            {
                prnt.print(" " + bruger);
            }
            prnt.println();
            */
            prnt.println(s);
        } else if (StringArray[0].equals("MSGRES"))
        {
            String[] temp = s.split(":");
            prnt.println(temp[0]+":" + getusername() + ":" + temp[1]);
            //prnt.println(StringArray[1] + " says: " + StringArray[2]);
        } else
        {
            //tilføj fejæl her   
        }
    }

    public String getusername() {
        return username;
    }
//
//    public String usersLoggedIn()
//    {
//        
//    }
    
}
 