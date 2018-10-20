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

        System.out.printf("********* Connexion %d started on %s%n", this.serviceNum, this.getServiceName());

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

    /**
     * Service specific code
     */
    abstract void serviceCore();

    /**
     * String representation of the service
     * @return a String representing the service name
     */
    protected abstract String getServiceName();

    /**
     * launch the service
     */
    public void launch () {
        new Thread(this).start();
    }

    /**
     * Get document object corresponding to the id given in String s
     * @param s String to parse to get the document id. Typically string to parse is : "bookID,subID" or "bookID"
     * @return the document corresponding to the id, or null if id doesn't match
     * @throws DocumentNotFound if the id doesn't match
     */
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

    /**
     * Get Subscriber object corresponding to the id given in String s
     * @param s String to parse to get the subscriber id; Typically string to parse is : "bookID,subID" or "bookID"
     * @return the subscriber corresponding to the id, or null if id doesn't match
     * @throws SubscriberNotFound if the id doesn't match
     */
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
