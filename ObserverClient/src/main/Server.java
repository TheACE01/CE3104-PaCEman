package main;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Server socket, establish a stream with clients from the main game and notifies the observer added
 * @author kevin Avevedo
 */
public class Server extends Observable implements Runnable {

    //Port number
    private Integer port;

    /**
     * Constructor method
     * @param puerto Server port
     */
    public Server(Integer puerto) {
        this.port = puerto;
    }

    /**
     * Init the socket and wait for new clients
     */
    @Override
    public void run() {
        ServerSocket servidor = null;
        Socket sc = null;
        DataInputStream in;

        try {
            //Create new Server
            servidor = new ServerSocket(port);
            System.out.println("Served turned on");

            //Always waiting for services
            while (true) {
                //waiting for new clients
                sc = servidor.accept();

                //init the input stream to receive messages
                in = new DataInputStream(sc.getInputStream());

                //Read a new message from clients
                String message = in.readUTF();

                //notifies the observer
                this.setChanged();
                this.notifyObservers(message);
                this.clearChanged();

                //close the connection
                sc.close();
            }
        } catch (IOException ex) {
            //Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
