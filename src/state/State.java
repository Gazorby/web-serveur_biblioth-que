package state;

import exception.NotAvailableException;
import library.Book;
import library.Subscriber;

import java.util.Timer;

public abstract class State {

    private Timer t;

    public void startTimer() {
        this.t = new Timer();
    }

    public Timer getTimer() {
        return this.t;
    }
    public abstract void reserv(Book book) throws NotAvailableException;

    /**
     * Allow a newSub to borrow the Doc
     * @throws NotAvailableException
     * @param book
     * @param subscriber
     */
    public abstract void borrow(Book book, Subscriber subscriber) throws NotAvailableException;

    /**
     * Allow a sub to give back the Doc
     * @param book
     */
    public abstract void back(Book book) throws NotAvailableException;
}
