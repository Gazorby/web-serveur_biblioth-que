package client;

import server.PORTS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

    static private final String HOST = "localhost";

    public static void main(String[] args) {

        Socket socket = null;
        try {
            PORTS port;
            String choice, line;

            // Make a Buffer from the standard input
            BufferedReader keyboard= new BufferedReader(new InputStreamReader(System.in));


            /*
            Cree une socket pour communiquer avec le service se trouvant sur la
            machine host au port PORT
            Cree les streams pour lire et ecrire du texte dans cette socket
            */
            port = getPort(keyboard);
            socket = new Socket(HOST, port.getValue());
            BufferedReader sin = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter sout = new PrintWriter(socket.getOutputStream(), true);

            // Informe l'utilisateur de la connection
            System.out.println("Connecté au serveur " + socket.getInetAddress() + ":" + socket.getPort());


            while(true) {
                // look for "bookNum,subNum" pattern
                do {
                    System.out.println("Give the book number and your subscriber id separated by a coma");
                    line = keyboard.readLine();

                    if (line == null) {break;}

                } while (!line.matches("(\\d+)[,](\\d+)"));


            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Close socket in all cases
        try { if (socket != null) socket.close(); }
        catch (IOException e2) {
        }
    }

    /**
     * Return the user choice
     * @param keyboard, bufferReader representing the keyboard
     * @return an int as the user choice
     * @throws IOException
     */
    private static PORTS getPort(BufferedReader keyboard) throws IOException {

        String choice;
        PORTS port;

        do {
            System.out.println("1.Borrow\n" +
                    "2.Reserv\n" +
                    "3.Bring back a doc\n" +
                    "Your choice : ");

            choice = keyboard.readLine();

            switch (choice) {
                case "1":
                    port = PORTS.BORROW_PORT;
                    break;
                case "2":
                    port = PORTS.RESERVATION_PORT;
                    break;
                case "3":
                    port = PORTS.BACK_PORT;
                    break;
                default:
                    port = PORTS.NONE;
            }

        } while (port == PORTS.NONE);

        return port;
    }
}