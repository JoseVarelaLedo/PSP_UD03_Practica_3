package practica3;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientHandler implements Runnable {
    private Socket clientSocket;

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            Scanner scanner = new Scanner(clientSocket.getInputStream());
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);

            // Leer a solicitude HTTP do cliente (ignorando as demais cabeceiras)
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.isEmpty()) {
                    break; // Fin da solicitude
                }
            }

            // Simular acceso ao contido
            ProcessBuilder processBuilder = new ProcessBuilder("echo", "¡Ola! Este é un servidor web simulado en Java.");
            Process process = processBuilder.start();

            // Ler a saída do proceso
            Scanner processScanner = new Scanner(process.getInputStream());
            StringBuilder content = new StringBuilder();
            while (processScanner.hasNextLine()) {
                content.append(processScanner.nextLine()).append("\n");
            }

            // Enviar resposta HTTP ao cliente
            writer.println("HTTP/1.1 200 OK");
            writer.println("Content-Type: text/plain");
            writer.println("Content-Length: " + content.length());
            writer.println();
            writer.print(content.toString());

            // Pechar recursos
            scanner.close();
            writer.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

