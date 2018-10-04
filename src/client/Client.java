package client;

import server.PORTS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

    static private final String HOST = "localhost";
    private static final int NO_CHOICE = -1;

    public static void main(String[] args) {

        Socket socket = null;
        try {
            int port;
            BufferedReader keyboard= new BufferedReader(new InputStreamReader(System.in));
            /*
            Cree une socket pour communiquer avec le service se trouvant sur la
            machine host au port PORT
            Cree les streams pour lire et ecrire du texte dans cette socket
            */

            do {
                System.out.println("1.Borrow\n" +
                                   "2.Reserv\n" +
                                   "3.Bring back a doc\n" +
                                   "Your choice : ");

                switch (keyboard.readLine()) {
                    case "1":
                        port = PORTS.BORROW_PORT.getPort();
                        break;
                    case "2":
                        port = PORTS.RESERVATION_PORT.getPort();
                        break;
                    case "3":
                        port = PORTS.BACK_PORT.getPort();
                        break;
                    default:
                        port = NO_CHOICE;
                }

            } while (port == NO_CHOICE);

            socket = new Socket(HOST, port);
            BufferedReader sin = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter sout = new PrintWriter(socket.getOutputStream(), true);
            /*
            Cree le stream pour lire du texte a partir du clavier
            (on pourrait aussi utiliser Scanner)
            */
            BufferedReader clavier = new BufferedReader(new InputStreamReader(System.in));

            // Informe l'utilisateur de la connection
            System.out.println("Connecté au serveur " + socket.getInetAddress() + ":" + socket.getPort());

            String line;

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Close socket in all cases
        try { if (socket != null) socket.close(); }
        catch (IOException e2) {
        }
    }
}