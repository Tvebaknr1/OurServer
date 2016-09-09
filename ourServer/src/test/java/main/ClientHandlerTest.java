/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.BeforeClass;

/**
 *
 * @author LouiseB
 */
public class ClientHandlerTest implements ObserverInterface
{

    private String responce = "";
    @BeforeClass
    public static void setUpClass(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OurSocket.main(null);
                } catch (IOException ex) {
                    Logger.getLogger(ClientHandlerTest.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ClientHandlerTest.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }).start();
        
    }
    @AfterClass
    public static void tearDownClass(){
        OurSocket.stopServer();
        
    }
    //adduser
    @org.junit.Test
    public void testAddUser() throws InterruptedException{
        OurSocket.register(this);
        OurSocket.addUsers("test");
        Thread.sleep(1000);
        assertEquals("CLIENTLIST:test", responce);
        OurSocket.deleteUsers("test");
        OurSocket.unRegister(this);
    }
    //msg
    @org.junit.Test
    public void testMSG() throws InterruptedException{
        OurSocket.register(this);
        OurSocket.MSG("MSG::TROLOLOL", "Sombra");
        Thread.sleep(1000);
        assertEquals("MSGRES:Sombra:TROLOLOL", responce);
        OurSocket.unRegister(this);
    }
    
    //deleteuser
    @org.junit.Test
    public void testDeleteUser() throws InterruptedException{
        OurSocket.register(this);
        OurSocket.addUsers("Test");
        OurSocket.deleteUsers("Test");
        Thread.sleep(1000);
        assertEquals("CLIENTLIST:", responce);
        OurSocket.unRegister(this);
    }
    //notify
    @org.junit.Test
    public void testNortify() throws InterruptedException{
        OurSocket.register(this);
        OurSocket.notifyObserver("test");
        Thread.sleep(1000);
        assertEquals("test", responce);
        OurSocket.unRegister(this);
    }

    @Override
    public String getusername() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(String s) {
        responce = s;
    }

}
