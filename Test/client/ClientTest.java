package client;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ClientTest {

    @Test
    void main() {
        String s = "12,12";
        assertEquals(true, s.matches("(\\d+)[,](\\d+)"));
    }
}