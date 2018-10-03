package services;

import java.net.Socket;

public class BackService extends Service {
    protected BackService(Socket client) {
        super(client);
    }

    @Override
    public void run() {

    }
}
