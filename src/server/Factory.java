package server;

import services.Service;

import java.net.Socket;

public interface Factory {

    Service getResService(Socket accept);

    Service getBorrowService(Socket accept);

    Service getBackService(Socket accept);
}
