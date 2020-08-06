package conections;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Client socket. This class is used to send the most important information of the game to the observer.
 * @author kevin Avevedo
 */
public class StreamingClient implements Runnable {

    //Server port
    private Integer port;

    //String message
    private String message;

    /**
     * Constructor method
     * @param port Server port
     * @param message String message
     */
    public StreamingClient(Integer port, String message) {
        this.port = port;
        this.message = message;
    }

    /**
     * Run method, when the thread is initialized
     */
    @Override
    public void run() {

        //the ip address of the observer
        final String HOST = "127.100.0.1";

        //Output Stream
        DataOutputStream out;

        try {
            //Creating a new Socket and connect to the server address and port
            Socket sc = new Socket(HOST, port);

            //create the output stream object
            out = new DataOutputStream(sc.getOutputStream());

            //Send a new message to the Observer
            out.writeUTF(message);

            //Close the socket
            sc.close();

        } catch (IOException ex) {
        }
    }

}
