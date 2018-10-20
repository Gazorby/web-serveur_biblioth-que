package services;

import exceptions.DocumentNotFound;
import exceptions.SubscriberNotFound;
import library.Document;
import library.Library;
import library.Subscriber;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public abstract class Service implements Runnable {

    private static int cpt = 1;
    protected final Socket client;
    int serviceNum;
    String line;
    PrintWriter out;

    protected Service(Socket client) {
        this.client = client;
        this.serviceNum = cpt++;
    }

    @Override
    public void run() {
        BufferedReader in;

        System.out.println("********* Connexion " + this.serviceNum + " started on " + this.getServiceName());

        try {
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            out = new PrintWriter(client.getOutputStream(), true);
            System.out.println("Connexion " + this.serviceNum);

            do {
                line = in.readLine();
                if (line.equals("stop") || line.equals("change")) {
                    break;
                }
                serviceCore();

            } while (true);

        } catch (IOException e) {}

    }

    abstract void serviceCore();

    protected abstract String getServiceName();

    public void launch () {
        new Thread(this).start();
    }

    Document getDocFromLine(String s) throws DocumentNotFound {
        int num = Integer.parseInt(s.split("[,]")[0]);
        Document document = Library.getDocument(num);

        if (document == null) {
            throw new DocumentNotFound(num);
        }

        else {
            return document;
        }
    }

    Subscriber getSubFromLine(String s) throws SubscriberNotFound {
        int num = Integer.parseInt(s.split("[,]")[1]);

        Subscriber subscriber = Library.getSubscriber(num);

        if (subscriber == null) {
            throw new SubscriberNotFound(num);
        }

        else {
            return subscriber;
        }
    }
}
