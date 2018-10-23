package library;

import exceptions.NotAvailableException;
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

    /**
     * Reserv this book to the sub given
     * @param sub the sub who want to reserv
     * @throws NotAvailableException if book isn't available
     */
    @Override
    public synchronized void reserv(Subscriber sub) throws NotAvailableException {
        if (sub.isAllowed()) {
            state.reserv(this, sub);
            this.sub = sub;
        }
        else throw new NotAvailableException("You're banned from book reservation");
    }

    /**
     * Allow sub given in parameter to borrow this book
     * @param sub who want to borrow
     * @throws NotAvailableException if this book isn't available
     */
    @Override
    public synchronized void borrow(Subscriber sub) throws NotAvailableException {
        if (sub.isAllowed()) {
            state.borrow(this, sub);
            this.sub = sub;
        }
        else throw new NotAvailableException("You're banned from borrowing");
    }

    @Override
    public synchronized void back() throws NotAvailableException {
        if (sub.isAllowed()) {
            state.back(this);
        }
        else throw new NotAvailableException("You can't back any document");
    }

    public void setState(State state) {
        this.state = state;
    }

    public Subscriber getSub() {
        return sub;
    }

    public Subscriber getSubscriber() {
        return this.sub;
    }
}
