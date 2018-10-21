package services;

import library.Library;
import server.Factory;

import java.net.Socket;

public class FactoryServices implements Factory {

    @Override
    public Service getResService(Socket accept, Library library) {
        return new ReservationService(accept, library);
    }

    @Override
    public Service getBorrowService(Socket accept, Library library) {
        return new BorrowService(accept, library);
    }

    @Override
    public Service getBackService(Socket accept, Library library) {
        return new BackService(accept, library);
    }
}
