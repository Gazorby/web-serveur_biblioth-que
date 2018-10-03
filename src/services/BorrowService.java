package services;

import java.net.Socket;

public class BorrowService extends Service {
    protected BorrowService(Socket client) {
        super(client);
    }

    @Override
    public void run() {

    }
}
