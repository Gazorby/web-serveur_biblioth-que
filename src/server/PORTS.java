package server;

public enum PORTS {
    RESERVATION_PORT (2500),
    BORROW_PORT (2600),
    BACK_PORT (2700),
    NONE(-1);

    private final int port;

    PORTS(int port) {
        this.port = port;
    }

    public int getValue() {
        return this.port;
    }
}
