package services;

import exceptions.AlreadyInMailingList;
import exceptions.DocumentNotFound;
import exceptions.NotAvailableException;
import exceptions.SubscriberNotFound;
import library.Document;
import library.Library;
import library.Subscriber;

import java.io.IOException;
import java.net.Socket;

public class ReservationService extends Service {
    protected ReservationService(Socket client, Library library) {
        super(client, library);
    }

    @Override
    public void run() {

        super.run();
        System.out.printf("********* Connexion %d terminated", this.serviceNum);

        try { client.close(); } catch (IOException e2) { }
    }

    @Override
    protected String getServiceName() {
        return "Reservation service";
    }

    void serviceCore() {
        try {
            Document document = super.getDocFromLine(line);
            Subscriber subscriber = super.getSubFromLine(line);
            tryReserv(document, subscriber);

        } catch (SubscriberNotFound | DocumentNotFound e) {
            out.println(e.getMessage());
        }
    }

    private void tryReserv(Document document, Subscriber subscriber) {

        String sout;

        try {
            document.reserv(subscriber);
            sout = String.format("[success] document n° %d is reserved by subscriber n° %d", document.getNum(), subscriber.getNum());

        } catch (NotAvailableException e) {
            sout = e.getMessage();

            try {
                library.addAlert(document, subscriber);

            } catch (AlreadyInMailingList e2) {
                sout = e2.getMessage();
            }
        }
        out.println(sout);
    }
}
