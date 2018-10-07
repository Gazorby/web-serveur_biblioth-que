package library;

import state.Available;
import state.State;

public abstract class GenericDocument implements Document {

    private int num;
    private State state;
    private Subscriber subscriber;

    public GenericDocument(int num) {
        this.num = num;
        this.state = new Available();
        this.subscriber = null;
    }

    public State getState() {
        return this.state;
    }

    public void setState(State state) {
        this.state = state;
    }

    @Override
    public int getNum() {
        return num;
    }

    public Subscriber getSubscriber() {
        return this.subscriber;
    }

    void setSubscriber(Subscriber subscriber) {
        this.subscriber = subscriber;
    }
}
