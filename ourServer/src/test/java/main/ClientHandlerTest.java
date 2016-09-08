/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import static org.junit.Assert.assertEquals;

/**
 *
 * @author LouiseB
 */
public class ClientHandlerTest
{

    @org.junit.Test
    public void testRun()
    {
        System.out.println("run");
        ClientHandler instance = new ClientHandler("localhost", "8080");
        instance.start();

        try
        {
            instance.join();
        } catch (InterruptedException ex)
        {
            System.out.println(ex.toString());
        }

        instance.run();

    }

    /**
     * Test of receive method, of class ClientHandler.
     */
    @org.junit.Test
    public void testReceive()
    {
        System.out.println("receive");
        ClientHandler instance = new ClientHandler("localhost", "8080");
        instance.start();
        try
        {
            instance.join();
        } catch (InterruptedException ex)
        {
            System.out.println(ex.toString());
        }

        instance.receive();
    }

    /**
     * Test of addUser method, of class ClientHandler.
     */
    @org.junit.Test
    public void testAddUser()
    {
        System.out.println("addUser");
        String username = "";
        ClientHandler instance = new ClientHandler("localhost", "8080");
        instance.start();
        try
        {
            instance.join();
        } catch (InterruptedException ex)
        {
            System.out.println(ex.toString());
        }
        instance.addUser(username);

    }

    /**
     * Test of writeMessage method, of class ClientHandler.
     */
    @org.junit.Test
    public void testWriteMessage()
    {
        System.out.println("writeMessage");
        String message = "";
        String[] users = null;
        ClientHandler instance = new ClientHandler("localhost", "8080");
        instance.start();

        try
        {
            instance.join();
        } catch (InterruptedException ex)
        {
            System.out.println(ex.toString());

        }
        instance.writeMessage(message, users);
    }

    /**
     * Test of logout method, of class ClientHandler.
     */
    @org.junit.Test
    public void testLogout()
    {
        System.out.println("logout");
        ClientHandler instance = new ClientHandler("localhost", "8080");
        instance.start();
        try
        {
            instance.join();
        } catch (InterruptedException ex)
        {
            System.out.println(ex.toString());

        }

        instance.logout();
    }

    /**
     * Test of getusername method, of class ClientHandler.
     */
    @org.junit.Test
    public void testGetusername()
    {
        System.out.println("getusername");
        ClientHandler instance = new ClientHandler("localhost", "8080");
        instance.start();
        try
        {
            instance.join();
        } catch (InterruptedException ex)
        {
            System.out.println(ex.toString());

        }

        String expResult = "";
        String result = instance.getusername();
        assertEquals(expResult, result);
    }

    /**
     * Test of update method, of class ClientHandler.
     */
    @org.junit.Test
    public void testUpdate()
    {
        System.out.println("update");
        String s = "";
        ClientHandler instance = new ClientHandler("localhost", "8080");
        instance.start();
        try
        {
            instance.join();
        } catch (InterruptedException ex)
        {
            System.out.println(ex.toString());

        }
        instance.update(s);
    }

    /**
     * Test of register method, of class ClientHandler.
     */
    @org.junit.Test
    public void testRegister()
    {
        System.out.println("register");
        ClientGUI gui = new ClientGUI();
        ClientHandler instance = new ClientHandler("localhost", "8080");
        instance.start();
        try
        {
            instance.join();
        } catch (InterruptedException ex)
        {
            System.out.println(ex.toString());
        }

        instance.register(gui);
    }

}
