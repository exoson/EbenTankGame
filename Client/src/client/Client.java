
package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client implements Runnable{
    
    private final Socket client;
    private final BufferedReader in;
    private final PrintWriter out;
    private boolean running;
    
    public Client() throws IOException
    {
        client = new Socket("194.197.235.200",8000);
        in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        out = new PrintWriter(client.getOutputStream()); 
    }
    
    public static void main(String[] args) 
    {
        Client c;
        try {
            c = new Client();
            new Thread(c).start();
            Scanner scan = new Scanner(System.in);
            boolean running = true;
            while(running){
                String output = scan.nextLine();
                c.sendMsg(output);
                if(output.equals("quit")) {
                    c.stop();
                    running = false;
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void stop()
    {
        running = false;
        try {
            client.close();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void sendMsg(String msg)
    {
        out.println(msg);
        out.flush();
    }
    @Override
    public void run() {
        running = true;
        while(running && !client.isClosed())
        {
            String input = "";
            try {
                input = in.readLine();
                System.out.println("Server: " + input);
                
            } catch (IOException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
