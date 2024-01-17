package servidorHTTP;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainProgram {

    public static void main(String[] args) {
        // lanzamos o servidor web
        ProcessBuilder processBuilder = new ProcessBuilder("java", "SimpleTextWebServer");
        try {
			processBuilder.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        // lanzamos 10 clientes cun pool para simular como sería nun servidor real
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            executorService.submit(new SimpleTextWebClientTask());
        }

        executorService.shutdown();
    }

    private static class SimpleTextWebClientTask implements Runnable {

        @Override
        public void run() {
            try {
                SimpleTextWebClient.main(new String[]{});
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
