package server;

import java.io.IOException;
import java.net.ServerSocket;


public class Server implements Runnable {
	private ServerSocket listenSocket;
	private PORTS port;
	private Factory factory;
	
	// Cree un server TCP - objet de la classe ServerSocket
	Server(PORTS port, Factory factory) throws IOException {
		listenSocket = new ServerSocket(port.getValue());
		this.factory = factory;
		this.port = port;
	}

	/** Le server ecoute et accepte les connexions.
	    pour chaque connexion, il cree un ServiceInversion,
	    qui va la traiter, et le lance
     */
	public void run() {
		try {
			while(true) {

				switch (port) {

					case RESERVATION_PORT:
						factory.getResService(listenSocket.accept()).launch();
						break;

					case BORROW_PORT:
						factory.getBorrowService(listenSocket.accept()).launch();
						break;

					case BACK_PORT :
						factory.getBackService(listenSocket.accept()).launch();
						break;

					default:
						break;
				}
			}

		}
		catch (IOException e) { 
			try {this.listenSocket.close();} catch (IOException e1) {}
			System.err.println("Pb sur le port d'�coute :"+e);
		}
	}
}
