package services;

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
    BufferedReader in;
    String line;
    PrintWriter out;

    protected Service(Socket client) {
        this.client = client;
        this.serviceNum = cpt++;
    }

    @Override
    public void run() {
        System.out.println("********* Connexion " + this.serviceNum + " started on " + this.getServiceName());

        try {
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            out = new PrintWriter(client.getOutputStream(), true);
            System.out.println("Connexion " + this.serviceNum);

        } catch (IOException e) {}

    }

    protected abstract String getServiceName();

    public void launch () {
        new Thread(this).start();
    }

    Document getDocFromLine(String s) {
        return Library.getDocument(Integer.parseInt(s.substring(0, 1)));
    }

    Subscriber getSubFromLine(String s) {
        return Library.getSubscriber(Integer.parseInt(s.substring(2,3)));
    }
}
