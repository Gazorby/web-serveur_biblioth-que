package services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public abstract class Service implements Runnable {

    private static int cpt = 1;
    protected final Socket client;
    int numero;
    BufferedReader in;
    String line;
    PrintWriter out;

    protected Service(Socket client) {
        this.client = client;
        this.numero = cpt++;
    }

    @Override
    public void run() {
        System.out.println("*********Connexion " + this.numero + " started on BackService");

        try {
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            out = new PrintWriter(client.getOutputStream(), true);
            System.out.println("Connexion " + this.numero);

        } catch (IOException e) {}

    }

    public void launch () {
        new Thread(this).start();
    }
}
