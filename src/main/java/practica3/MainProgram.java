package practica3;

public class MainProgram {
	 public static void main(String[] args) {
	        int numberOfClients = 10;
	        for (int i = 0; i < numberOfClients; i++) {
	            Thread clientThread = new Thread(new SimpleTextWebClientTask());
	            clientThread.start();
	        }
	    }
}
