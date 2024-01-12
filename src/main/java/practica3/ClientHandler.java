package practica3;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientHandler implements Runnable {
    private Socket clienteSocket;

    public ClientHandler(Socket clienteSocket) {
        this.clienteSocket = clienteSocket;
    }

    @Override
    public void run() {
        try {
            Scanner lectorSocket = new Scanner(clienteSocket.getInputStream());
            PrintWriter printWriter = new PrintWriter(clienteSocket.getOutputStream(), true);

            
            while (lectorSocket.hasNextLine()) {
                String line = lectorSocket.nextLine();
                if (line.isEmpty()) {
                    break; 
                }
            }
            
            ProcessBuilder processBuilder = new ProcessBuilder("echo", "¡Ola! Este é un servidor web simulado en Java.");
            Process proceso = processBuilder.start();

           
            Scanner lecturaProceso = new Scanner(proceso.getInputStream());
            StringBuilder contidoResposta = new StringBuilder();
            while (lecturaProceso.hasNextLine()) {
                contidoResposta.append(lecturaProceso.nextLine()).append("\n");
            }
            
            
            //engadimos o contido das respostas
            printWriter.println("\n------------------\nResposta do servidor:\n-----------------");
            printWriter.println("\"HTTP/1.1 200 OK\"");
            printWriter.println("Contido.......");
            printWriter.println("Lonxitude resposta: " + contidoResposta.length());
            printWriter.println();
            printWriter.print(contidoResposta.toString());
          
            lectorSocket.close();
            printWriter.close();
            clienteSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

