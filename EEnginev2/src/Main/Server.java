
package Main;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server implements Runnable{
    
    private final ArrayList<ClientServer> cs = new ArrayList<>();
    private int clientamt = 0;
    private boolean running;
    
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
    public void message(String msg, int clientIdx) {
        getCs().get(clientIdx).sendMsg(msg);
    }
    public void broadcast(String msg)
    {
        for(ClientServer c : getCs()){
            c.sendMsg(msg);
        }
    }
    public void stop()
    {
        running = false;
        for(ClientServer c : getCs()) {
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
                    getCs().add(new ClientServer(this, client));
                    System.out.println("Client connected " + client.getInetAddress().toString());
                    clientamt++;
                    new Thread(getCs().get(clientamt - 1)).start();
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    /**
     * @return the cs
     */
    public ArrayList<ClientServer> getCs() {
        return cs;
    }
}
