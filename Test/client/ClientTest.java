package client;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ClientTest {

    @Test
    void main() {
        String s = "12,12";
        assertTrue(s.matches("(\\d+)[,](\\d+)"));
    }
}