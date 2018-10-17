package services;

import exception.NotAvailableException;
import library.Document;
import library.Subscriber;

import java.io.IOException;
import java.net.Socket;

public class BorrowService extends Service {
    protected BorrowService(Socket client) {
        super(client);
    }

    @Override
    public void run() {
        try {
            super.run();

            do {
                line = in.readLine();
                if (line.equals("stop") || line.equals("change")) { break; }
                borrow();

            } while (true);

        } catch (IOException e) {}

        System.out.println("********* Connexion " + this.serviceNum + " terminated");

        try { client.close(); } catch (IOException e2) { }
    }

    @Override
    protected String getServiceName() {
        return "Borrow service";
    }

    private void borrow() {
        try {
            Document document = super.getDocFromLine(line);
            Subscriber subscriber = super.getSubFromLine(line);

            document.borrow(subscriber);
            out.println("You borrowed document " + document.getNum());
        } catch (NotAvailableException e) {
            out.println(e.getMessage());
        }
    }
}
