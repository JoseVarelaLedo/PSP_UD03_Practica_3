package servidorHTTP;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class SimpleTextWebClient {
	 private static final String HOST = "localhost";
     private static final int PORTO = 8080;


    public static void main(String[] args) throws IOException {
        Socket socket;
   		try {
   			socket = new Socket(HOST, PORTO);
   			// enviar solicitude HTTP GET
   			// usamos PrintWriter tras ler a documentación que di que é máis recomendable
   	        PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
   	        //mensaxe sacada de google
   	        printWriter.print("GET / HTTP/1.1\nHost: localhost\n\n");
   	        printWriter.flush();

   	        // ler resposta do servidor
   	        InputStream inputStream = socket.getInputStream();
   	        byte[] resposta = new byte[1024];
   	        int bytesLidos = inputStream.read(resposta);
   	        String responseString = new String(resposta, 0, bytesLidos);

   	        // amosar resposta do servidor por consola
   	        System.out.println("Resposta do servidor: " + responseString);
   	        socket.close();
   		} catch (UnknownHostException e) {			
   			e.printStackTrace();
   		} catch (IOException e) {			
   			e.printStackTrace();
   		}
    }
}