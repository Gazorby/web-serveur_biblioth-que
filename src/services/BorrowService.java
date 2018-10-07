package services;

import exception.NotAvailableException;
import library.Library;
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
                // debug
                line = in.readLine();
                if (line.equals("stop") || line.equals("change")) { break; }
                borrow();

            } while (true);

        } catch (IOException e) {}

        System.out.println("*********Connexion " + this.numero + " terminated");

        try { client.close(); } catch (IOException e2) { }
    }

    private void borrow() {
        int docNum = Integer.parseInt(line.substring(0, 1));
        int subNum = Integer.parseInt(line.substring(2,3));
        Subscriber subscriber = Library.subscribers.get(subNum);

        try {
            Library.books.get(docNum).borrow(subscriber);
            out.println("You borrowed document " + docNum);
        } catch (NotAvailableException e) {
            out.println(e.getMessage());
        }
    }
}
