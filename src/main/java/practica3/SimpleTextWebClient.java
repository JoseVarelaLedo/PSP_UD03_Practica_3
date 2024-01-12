package practica3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class SimpleTextWebClient {
	public static void main(String[] args) {
		final int PORTO = 8080;
		try {
			Socket socket = new Socket("localhost", PORTO);
			BufferedReader bufferReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			// dende aquí envíanse as peticións "GET"
			socket.getOutputStream().write("GET / HTTP/1.1\r\n\r\n".getBytes());
			socket.getOutputStream().flush();

			// lectura da resposta do servidor
			String resposta;
			while ((resposta = bufferReader.readLine()) != null) {
				System.out.println(resposta);
			}
			bufferReader.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}