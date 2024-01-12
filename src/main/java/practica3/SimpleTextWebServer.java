package practica3;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimpleTextWebServer {	
    public static void main(String[] args) {
		final int PORTO = 8080;
        try {
            ServerSocket serverSocket = new ServerSocket(PORTO);
            System.out.println("Servidor web simulado en execución. Escoitando no porto " + PORTO + "...");

            //definimos pool de fíos como sucedería nun servidor web real            
            ExecutorService poolClientes = Executors.newFixedThreadPool(10);

            while (true) {
                Socket clienteSocket = serverSocket.accept();
                poolClientes.execute(new ClientHandler(clienteSocket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}