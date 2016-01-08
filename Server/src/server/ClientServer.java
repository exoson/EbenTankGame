
package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientServer implements Runnable
{
    private final Server server;
    private final Socket clientSocket;
    private final BufferedReader in;
    private final PrintWriter out;
    private boolean running;
    
    public ClientServer(Server s, Socket client) throws IOException{
        client.setSoTimeout(0);
        client.setKeepAlive(true);
        clientSocket = client;
        server = s;
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        out = new PrintWriter(clientSocket.getOutputStream());
    }

    @Override
    public void run() {
        running = true;
        while(running && !clientSocket.isClosed()){
            String input = "";
            try {
                input = in.readLine();
                if(input.equals("quit")) stop();
                else System.out.println("Client: " + input);
                
            } catch (IOException ex) {
                Logger.getLogger(ClientServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public void sendMsg(String msg)
    {
        out.println(msg);
        out.flush();
    }
    public void stop()
    {
        running = false;
        try {
            clientSocket.close();
            System.out.println("Client disconnected: " + clientSocket.getInetAddress().toString());
        } catch (IOException ex) {
            Logger.getLogger(ClientServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
