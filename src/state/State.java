package state;

import exception.NotAvailableException;
import library.Book;
import library.Subscriber;

public abstract class State {

    Subscriber subscriber;

    public abstract void reserv(Subscriber subscriber, Book book) throws NotAvailableException;

    /**
     * Allow a newSub to borrow the Doc
     * @throws NotAvailableException
     * @param subscriber
     * @param book
     */
    public abstract void borrow(Subscriber subscriber, Book book) throws NotAvailableException;

    /**
     * Allow a sub to give back the Doc
     * @param book
     */
    public abstract void back(Book book) throws NotAvailableException;
}
