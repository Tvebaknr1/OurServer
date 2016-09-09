/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author ehn19
 */
public class FileWriterClass extends Thread
{
    ArrayList<String> lines = new ArrayList();
    
    public void addLine(String s)
    {
        lines.add(s);
    }
    public void writeFile() throws IOException
    {
        FileWriter writer = new FileWriter("TextDoc.txt", false);
        PrintWriter out = new PrintWriter(writer);

        String l = "";

        for (String line : lines)
        {
            l += line + "\n";
            out.println(line);
        }
        out.close();
    }

    @Override
    public void run()
    {
        while (true)
        {
            try
            {
                Thread.sleep(15000);
            } catch (InterruptedException ex)
            {
                System.out.println(ex.toString());
            }
            try
            {
                writeFile();

            } catch (IOException ex)
            {
                System.out.println(ex.toString());
            }
        }
    }
}
