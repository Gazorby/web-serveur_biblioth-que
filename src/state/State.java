package state;

import exceptions.NotAvailableException;
import library.Book;
import library.Subscriber;

public abstract class State {

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
     */
    public abstract void back(Book book);
}
