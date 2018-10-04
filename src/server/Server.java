package server;

import java.io.IOException;
import java.net.ServerSocket;


class Server implements Runnable {
	private ServerSocket listen_socket;
	private int port;
	private Factory factory;
	
	// Cree un server TCP - objet de la classe ServerSocket
	Server(int port, Factory factory) throws IOException {
		listen_socket = new ServerSocket(port);
		this.factory = factory;
	}

	/** Le server ecoute et accepte les connexions.
	    pour chaque connexion, il cree un ServiceInversion,
	    qui va la traiter, et le lance
     */
	public void run() {
		try {
			while(true) {

				switch (port) {

					case 2500 :
						factory.getResService(listen_socket.accept()).launch();
						break;

					case 2600 :
						factory.getBorrowService(listen_socket.accept()).launch();
						break;

					case 2700 :
						factory.getBackService(listen_socket.accept()).launch();
						break;
				}
			}

		}
		catch (IOException e) { 
			try {this.listen_socket.close();} catch (IOException e1) {}
			System.err.println("Pb sur le port d'écoute :"+e);
		}
	}

	 // restituer les ressources --> finalize
	protected void finalize() {
		try {this.listen_socket.close();} catch (IOException e1) {}
	}
}
