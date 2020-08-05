import java.net.*;
        import java.io.*;


public class SocketCliente {

    public static void main(String[] args) throws IOException {
      cliente SocketCliente = new cliente("localhost", 8081);
      System.out.println(SocketCliente.ReciveMessage());
      SocketCliente.SendMessage("dot,7");
    }
}