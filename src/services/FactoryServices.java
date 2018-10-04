package services;

import server.Factory;

import java.net.Socket;

public class FactoryServices implements Factory {
    @Override
    public Service getResService(Socket accept) {
        return new ReservationService(accept);
    }

    @Override
    public Service getBorrowService(Socket accept) {
        return new BorrowService(accept);
    }

    @Override
    public Service getBackService(Socket accept) {
        return new BackService(accept);
    }
}
