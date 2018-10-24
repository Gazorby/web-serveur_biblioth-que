package services;

import org.junit.jupiter.api.Test;
import server.PORTS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReservationServiceTest {

    @Test
    String reserv(String line) {

        try (Socket socket = new Socket("localhost", PORTS.RESERVATION_PORT.getValue());
             BufferedReader sin = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter sout = new PrintWriter(socket.getOutputStream(), true) )
        {
            sout.println(line);
            return sin.readLine();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "borrow test failed";
    }

    @Test
    void reservTest() {
        String expected = "[success] document n° 1 is reserved by subscriber n° 1";
        assertEquals(expected, reserv("1,1"));
    }

    @Test
    void alreadyReservedTest() {
        reserv("1,1");

        String expected = "[error] Document n° 1 is already reserved by subscriber n° 1\"";
        assertEquals(expected, reserv("1,2"));
    }
}