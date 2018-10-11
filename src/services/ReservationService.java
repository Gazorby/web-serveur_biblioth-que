package services;

import exception.NotAvailableException;
import library.Document;
import server.DELAYS;

import java.io.IOException;
import java.net.Socket;

public class ReservationService extends Service {
    protected ReservationService(Socket client) {
        super(client);
    }

    @Override
    public void run() {
        try {
            super.run();

            do {
                line = in.readLine();
                if (line.equals("stop") || line.equals("change")) { break; }
                reserv();

            } while (true);

        } catch (IOException e) {}

        System.out.println("********* Connexion " + this.serviceNum + " terminated");

        try { client.close(); } catch (IOException e2) { }
    }

    @Override
    protected String getServiceName() {
        return "Reservation service";
    }

    private void reserv() {
        try {
            Document document = super.getDocFromLine(line);
            document.reserv(super.getSubFromLine(line));
            out.println(String.format("You reserved document %d for %d sec", document.getNum(), DELAYS.RES_DELAY.getValue() / 1000));
        } catch (NotAvailableException e) {
            out.println(e.getMessage());
        }
    }
}
