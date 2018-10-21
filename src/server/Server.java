package server;

import library.Library;

import java.io.IOException;
import java.net.ServerSocket;


public class Server implements Runnable {
	private ServerSocket listenSocket;
	private PORTS port;
	private Factory factory;
	private Library library;
	
	// Create a TCP server
	Server(PORTS port, Factory factory) throws IOException {
		listenSocket = new ServerSocket(port.getValue());
		this.factory = factory;
		this.port = port;
		this.library = Library.getInstance();
	}

	/** Server is listening for connections and accept them.
	    for each connection, it instantiate and launch the corresponding service
     */
	public void run() {
		try {
			while(true) {

				switch (port) {

					case RESERVATION_PORT:
						factory.getResService(listenSocket.accept(), library).launch();
						break;

					case BORROW_PORT:
						factory.getBorrowService(listenSocket.accept(), library).launch();
						break;

					case BACK_PORT :
						factory.getBackService(listenSocket.accept(), library).launch();
						break;

					default:
						break;
				}
			}

		}
		catch (IOException e) { 
			try {this.listenSocket.close();} catch (IOException e1) {}
			System.err.println("Error on listening port :"+e);
		}
	}
}
