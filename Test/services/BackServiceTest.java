package services;

import org.junit.jupiter.api.Test;
import server.PORTS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BackServiceTest {

    @Test
    void testBack() {

        BorrowServiceTest before = new BorrowServiceTest();
        before.borrow("1,1");

        try (Socket socket = new Socket("localhost", PORTS.BACK_PORT.getValue());
             BufferedReader sin = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter sout = new PrintWriter(socket.getOutputStream(), true) )
        {

            String sent = "1,1,0";
            String expected = "[success] document n° 1 is back !";
            sout.println(sent);
            assertEquals(expected, sin.readLine());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}