package conections;

import main.Game;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;


public class Client implements Runnable {

    private Socket socket;
    private String hostname="localhost";
    private  int port=8081;
    private Game game;

    public Client(String hostname, int port, Game game) throws IOException {
        this.hostname = hostname;
        this.port = port;
        this.game = game;
        this.socket = new Socket(hostname, port);
    }

    public void SendMessage(String message){

        try  {
            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);
            writer.println(message);
            //Estos catch son obligatorios para la clase que crea el socket
            // Lo que haga tiene que ir dentro de este try/catch
        } catch (UnknownHostException ex) {

            System.out.println("Server not found: " + ex.getMessage());

        } catch (IOException ex) {

            System.out.println("I/O error: " + ex.getMessage());
        }
    }


    @Override
    public void run() {
        try  {

            while (true){
            InputStream input = socket.getInputStream();
            InputStreamReader reader = new InputStreamReader(input);
            int character;
            StringBuilder data = new StringBuilder();

            while ((character = reader.read()) != -1) {
                if ((char) character =='.') // indica al cliente que es el final
                    break;
                else{
                    data.append((char) character);
                }
            }

            System.out.println(data.toString());

            //send the message to the main Game class
            game.redirectServerMessage(data.toString());
            }

        } catch (UnknownHostException ex) {

            System.out.println("Server not found: " + ex.getMessage());

        } catch (IOException ex) {
            System.out.println("I/O error: " + ex.getMessage());

        }
    }
}
