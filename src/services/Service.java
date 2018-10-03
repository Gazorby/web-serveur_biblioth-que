package services;

import java.net.Socket;

public abstract class Service implements Runnable {

    private final Socket client;

    protected Service(Socket client) {
        this.client = client;
    }

    @Override
    public abstract void run();

    public void launch() {
        new Thread(this).start();
    }
}
