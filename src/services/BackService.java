package services;

import exceptions.DocumentNotFound;
import library.Document;
import library.Library;

import java.io.IOException;
import java.net.Socket;

public class BackService extends Service {

    public BackService(Socket client, Library library) {
        super(client, library);
    }

    @Override
    public void run() {

        super.run();
        System.out.printf("********* Connexion %d terminated%n", this.serviceNum);

        try {
            client.close();
        } catch (IOException e2) {
        }
    }

    @Override
    protected String getServiceName() {
        return "Back Service";
    }

    @Override
    void serviceCore() {
        try {
            Document document = super.getDocFromLine(line);
            document.back();
            library.sendAlert(document.getNum());
            out.println(String.format("[success] document n° %d is back !", document.getNum()));

        } catch (DocumentNotFound documentNotFound) {
            out.println(documentNotFound.getMessage());
        }
    }
}

