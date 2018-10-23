package services;

import exceptions.DocumentNotFound;
import exceptions.NotAvailableException;
import exceptions.SubscriberNotFound;
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
            int info = super.getInfoFromLine(line);
            Document document = super.getDocFromLine(line);
            document.back();
            if (info == 0) {
                library.sendAlert(document.getNum());
                out.println(String.format("[success] document n° %d is back !", document.getNum()));
            }
            else {
                library.sendAlert(document.getNum());
                out.println(String.format("[success] document n° %d is back ! But you're banned from borrowing for 1 month", document.getNum()));super.getSubFromLine(line).updateStatus();
            }

        } catch (DocumentNotFound documentNotFound) {
            out.println(documentNotFound.getMessage());
        } catch (SubscriberNotFound subscriberNotFound) {
            subscriberNotFound.printStackTrace();
        } catch (NotAvailableException e) {
            e.printStackTrace();
        }
    }
}

