package server;

import services.FactoryServices;

import java.io.IOException;

public class App {

    private static Factory factory;

    public static void main(String[] args) {

        try {
            // instanciate factory
            factory = new FactoryServices();

            new Thread(new Server(PORTS.RESERVATION_PORT.getValue(), factory)).start();
            new Thread(new Server(PORTS.BORROW_PORT.getValue(), factory)).start();
            new Thread(new Server(PORTS.BACK_PORT.getValue(), factory)).start();
            System.out.println("Servers started on ports 2500, 2600 and 2700");


        } catch (IOException e) {
            System.err.println("Error on server startup : " +  e);
        }
    }
}
