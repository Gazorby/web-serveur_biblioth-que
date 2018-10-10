package library;

import exception.NotAvailableException;
import state.Available;
import state.State;

public class Book implements Document {

    private int num;
    private State state;
    private Subscriber sub;

    public Book(int num) {
        this.num = num;
        state = new Available();
    }

    @Override
    public int getNum() {
        return this.num;
    }

    @Override
    public void reserv(Subscriber sub) throws NotAvailableException {
        state.reserv(this);
        this.sub = sub;
    }

    @Override
    public void borrow(Subscriber sub) throws NotAvailableException {
            state.borrow(this, sub);
    }

    @Override
    public void back(Subscriber sub) throws NotAvailableException {
        state.back(this);
    }

    public void setState(State state) {
        this.state = state;
    }

    public Subscriber getSub() {
        return sub;
    }

    public void setSub(Subscriber sub) {
        this.sub = sub;
    }
}
