package server;

import library.Library;
import services.Service;

import java.net.Socket;

public interface Factory {

    /**
     * Return a reservation service
     * @param accept, the server socket
     * @return a reservation service
     */
    Service getResService(Socket accept, Library library);

    /**
     * Return a borrow service
     * @param accept, the server socket
     * @return a borrow service
     */
    Service getBorrowService(Socket accept, Library library);

    /**
     * Return a Back service
     * @param accept, the server socket
     * @return a Back service
     */
    Service getBackService(Socket accept, Library library);
}
