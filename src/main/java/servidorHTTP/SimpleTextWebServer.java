package servidorHTTP;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleTextWebServer {

    private static final int PORTO = 8080;

    public static void main(String[] args) {
    	ServerSocket serverSocket;
    	
    	String mensaxeBenvida = "Servidor á escoita no porto " + PORTO;
        System.out.println (mensaxeBenvida);
        
		try {
			serverSocket = new ServerSocket(PORTO);
			 while (true) {
		            Socket cliente = serverSocket.accept();

		            // creamos un fío para manexar a solicitude do cliente
		            Thread fioCliente = new Thread(new ClientHandler(cliente));
		            fioCliente.start();
		        }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    
    }

    private static class ClientHandler implements Runnable {

        private final Socket socket;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                // obtemos o fluxo de entrada do cliente
                InputStream is = socket.getInputStream();

                // lemos a solicitude do cliente
                byte[] peticionCliente = new byte[1024];
                is.read(peticionCliente);

                // simulamos o acceso ao contido do servidor
                ProcessBuilder processBuilder = new ProcessBuilder("echo", "Ola! Este é un servidor web simulado en Java.");
                processBuilder.start();

                //mensaxe sacada de google 
                String resposta = "HTTP/1.1 200 OK\n\n ¡Ola! Este é un servidor web simulado en Java.";

                // enviamos resposta ao cliente
                OutputStream os = socket.getOutputStream();
                os.write(resposta.getBytes());

                // pechamos aa conexión co cliente
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
