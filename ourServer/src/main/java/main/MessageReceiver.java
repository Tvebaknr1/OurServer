/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.Scanner;

/**
 *
 * @author Emil
 */
public class MessageReceiver extends Thread implements StaticSubjectInterface
{

    Scanner input;
    ObserverInterface o;

    public MessageReceiver(Scanner input)
    {
        this.input = input;
    }

    @Override
    public void run()
    {
        while (true)
        {
            String msg = input.next();
            if(msg.startsWith("CLIENTLIST:") || msg.startsWith("MSGRES:"))
            {
                notifyObserver(msg);
            }else
            {
                System.out.println("Error: " + msg);
            }

        }
    }

    public void register(ObserverInterface o)
    {
        this.o = o;
    }

    public void notifyObserver(String s)
    {
        o.update(s);
    }
}
