package services;

import library.Library;

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
                if (line.equals("stop")) { break; }

                // debug
                System.out.println(Library.books.get(Integer.parseInt(line.substring(0, 1))).getNum());

            } while (true);

        } catch (IOException e) {}

        System.out.println("*********Connexion " + this.numero + " terminated");

        try { client.close(); } catch (IOException e2) { }
    }
}
