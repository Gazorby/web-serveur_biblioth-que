package client;

import server.PORTS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class App {

    private static final String HOST = "localhost";

    public static void main(String[] args) throws IOException {

        String line;

        /*
         try-with-resource is used to ensure that all resources will be closed

         Resource :
          - BufferedReader reading the standard input (keyboard)
          - Socket
          - BufferedReader reading the socket OutputStream
          - PrintWriter writing to the socket InputStream
          */

        try (   BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
                Socket socket = new Socket(HOST, getPort(keyboard));
                BufferedReader sin = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter sout = new PrintWriter(socket.getOutputStream(), true)) {
            // Inform the user he's connected
            System.out.println("Connecté au serveur " + socket.getInetAddress() + ":" + socket.getPort());


            do {
                if (socket.getPort() == PORTS.BACK_PORT.getValue()) {
                    System.out.println("Give the book number\n" +
                                       "enter \"stop\" to quit, or \"change\" to change service");
                }

                else {
                    System.out.println("Give the book number and your subscriber id separated by a coma\n" +
                                       "enter \"stop\" to quit, or \"change\" to change service");
                }

                line = keyboard.readLine();

                // look for "int,int" pattern OR "stop" OR "change"
                if (checkLine(socket, line)) {
                    sout.println(line);

                    if (line.equals("change")) {
                        App.main(new String[1]);
                        System.out.println("\n");
                        System.exit(0);
                    }
                }

                System.out.println(sin.readLine());

            } while (!line.equals("stop"));
        }
    }
        /**
         * Return the user choice
         * @param keyboard, bufferReader representing the keyboard
         * @return an int as the user choice
         * @throws IOException
         */
        private static int getPort (BufferedReader keyboard) throws IOException {

            String choice;
            PORTS port;

            do {
                System.out.println( "1.Borrow\n" +
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

            return port.getValue();
        }

        private static boolean checkLine(Socket socket, String s) {

            String borrowOrReserv = "((\\d+)[,](\\d+))";
            String back = "(\\d)";

            return s.matches(back) && socket.getPort() == PORTS.BACK_PORT.getValue() ||
                    s.matches(borrowOrReserv) || s.equals("stop") || s.equals("change");
        }
}