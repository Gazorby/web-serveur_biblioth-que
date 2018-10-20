package server;

import services.FactoryServices;

import java.io.IOException;

public class App {

    public static void main(String[] args) {

        try {
            // instantiate factory
            Factory factory = new FactoryServices();
            new Thread(new Server(PORTS.RESERVATION_PORT, factory)).start();
            new Thread(new Server(PORTS.BORROW_PORT, factory)).start();
            new Thread(new Server(PORTS.BACK_PORT, factory)).start();
            System.out.println("Servers started on ports 2500, 2600 and 2700");


        } catch (IOException e) {
            System.err.println("Error on server startup : " +  e);
        }
    }
}
