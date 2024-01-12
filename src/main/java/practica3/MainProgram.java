package practica3;

public class MainProgram {
	 public static void main(String[] args) {
	        int numConexionsClientes = 10;
	        for (int i = 0; i < numConexionsClientes; i++) {
	            Thread clienteThread = new Thread(new SimpleTextWebClientTask());
	            clienteThread.start();
	        }
	    }
}
