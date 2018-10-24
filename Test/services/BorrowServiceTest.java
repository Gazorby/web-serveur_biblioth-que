package services;

import org.junit.jupiter.api.Test;
import server.PORTS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BorrowServiceTest {

    @Test
    String borrow(String line) {
        try (Socket socket = new Socket("localhost", PORTS.BORROW_PORT.getValue());
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
    void simpleTest() {
        String expected = "[success] document 1 is borrowed by subscriber n° 1";
        assertEquals(expected, borrow("1,1"));
    }

    @Test
    void alreadyBorrowed() {
        borrow("1,1");
        String expected = "[error] Document n° 1 is already borrowed by subscriber n° 1 (Subscriber n° 2 will be notified by email when the doc will be back)";
        assertEquals(expected, borrow("1,2"));
    }
}