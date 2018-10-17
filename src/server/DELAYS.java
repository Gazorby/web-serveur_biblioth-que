package server;

public enum DELAYS {
    RES_DELAY(10000);

    private final int delay;

    DELAYS(int delay) {
        this.delay = delay;
    }

    public int getValue() {
        return this.delay;
    }
}
