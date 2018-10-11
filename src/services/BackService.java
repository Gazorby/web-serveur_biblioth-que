package services;

import exception.NotAvailableException;
import library.Document;

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
                line = in.readLine();
                if (line.equals("stop") || line.equals("change")) { break; }
                back();

            } while (true);

        } catch (IOException e) {}

        System.out.println("********* Connexion " + this.serviceNum + " terminated");

        try { client.close(); } catch (IOException e2) { }
    }

    @Override
    protected String getServiceName() {
        return "Back Service";
    }

    private void back() {

        try {
            Document document = super.getDocFromLine(line);
            document.back(super.getSubFromLine(line));
            out.println("You bring back document " + document.getNum());
        } catch (NotAvailableException e) {
            out.println(e.getMessage());
        }
    }
}

