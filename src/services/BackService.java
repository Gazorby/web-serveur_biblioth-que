package services;

import exception.NotAvailableException;
import library.Library;
import library.Subscriber;

import java.io.IOException;
import java.net.Socket;

public class BackService extends Service {

    public BackService(Socket client) {
        super(client);
    }

    @Override
    public void run() {

        try {
            super.run();

            do {
                // debug
                line = in.readLine();
                if (line.equals("stop")) { break; }
                back();

                // debug
                System.out.println(Library.books.get(Integer.parseInt(line.substring(0, 1))).getNum());

            } while (true);

        } catch (IOException e) {}

        System.out.println("*********Connexion " + this.numero + " terminated");

        try { client.close(); } catch (IOException e2) { }
    }

    private void back() {
        int docNum = Integer.parseInt(line.substring(0, 1));
        int subNum = Integer.parseInt(line.substring(2,3));
        Subscriber subscriber = Library.subscribers.get(subNum);

        try {
            Library.books.get(docNum).back(subscriber);
            out.println("You bring back document " + docNum);
        } catch (NotAvailableException e) {
            out.println(e.getMessage());
        }
    }
}

