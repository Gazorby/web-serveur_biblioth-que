package services;

import exceptions.DocumentNotFound;
import exceptions.NotAvailableException;
import exceptions.SubscriberNotFound;
import library.Document;
import library.Library;
import library.Subscriber;

import java.io.IOException;
import java.net.Socket;

public class BorrowService extends Service {
    protected BorrowService(Socket client, Library library) {
        super(client, library);
    }

    @Override
    public void run() {
        super.run();
        System.out.printf("********* Connexion %d terminated%n", this.serviceNum);

        try { client.close(); } catch (IOException e2) { }
    }

    @Override
    protected String getServiceName() {
        return "Borrow service";
    }

    @Override
    void serviceCore() {
        try {
            Document document = super.getDocFromLine(line);
            Subscriber subscriber = super.getSubFromLine(line);
            document.borrow(subscriber);
            out.println(String.format("[success] document %d is borrowed by subscriber n° %d", document.getNum(), subscriber.getNum()));

        } catch (NotAvailableException | DocumentNotFound | SubscriberNotFound e) {
            out.println(e.getMessage());
        }
    }
}
