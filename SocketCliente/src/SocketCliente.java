import java.net.*;
        import java.io.*;
import java.util.Scanner;


public class SocketCliente {

    public static void main(String[] args) throws IOException {
        cliente c = new cliente("localhost", 8081);
        Thread thread = new Thread(c);
        thread.start();

            while(true){
                Scanner scan = new Scanner(System.in);
                String message = scan.nextLine();
                c.SendMessage(message);
            }
        }
    }
