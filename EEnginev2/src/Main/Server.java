
package Main;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server implements Runnable{
    
    private ArrayList<ClientServer> cs = new ArrayList<>();
    private int clientamt = 0;
    boolean running;
    
    public static void main(String[] args) {
        Server s;
        s = new Server();
        Thread t = new Thread(s);
        t.start();
        Scanner scan = new Scanner(System.in);
        boolean running = true;
        System.out.println("Server running");
        while(running){
            String input = scan.nextLine();
            s.broadcast(input);
            if(input.equals("quit")){
                s.stop();
                running = false;
            }
        }
    }
    public void broadcast(String msg)
    {
        for(ClientServer c : cs){
            c.sendMsg(msg);
        }
    }
    private void stop()
    {
        running = false;
        for(ClientServer c : cs) {
            c.stop();
        }
    }
    @Override
    public void run()
    {
        try (ServerSocket ss = new ServerSocket(8000)) {
            ss.setSoTimeout(1000);
            running = true;
            while(running){
                Socket client = null;
                try {
                    client = ss.accept();
                }
                catch(IOException e){
                    
                }
                if(client != null){
                    cs.add(new ClientServer(this, client));
                    System.out.println("Client connected " + client.getInetAddress().toString());
                    clientamt++;
                    new Thread(cs.get(clientamt - 1)).start();
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
