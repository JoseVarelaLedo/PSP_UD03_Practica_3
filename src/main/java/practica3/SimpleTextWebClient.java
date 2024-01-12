package practica3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class SimpleTextWebClient {
	 public static void main(String[] args) {
	        try {
	            Socket socket = new Socket("localhost", 8080);
	            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

	            // Enviar solicitude HTTP GET ao servidor
	            socket.getOutputStream().write("GET / HTTP/1.1\r\n\r\n".getBytes());
	            socket.getOutputStream().flush();

	            // Ler e amosar a resposta do servidor
	            String line;
	            while ((line = reader.readLine()) != null) {
	                System.out.println(line);
	            }

	            // Pechar recursos
	            reader.close();
	            socket.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
}
