package state;

import exceptions.NotAvailableException;
import library.Book;
import library.Subscriber;

public abstract class State {

    /**
     * Allow a sub to reserv a specific book
     * @param book, the book to be reserved
     * @throws NotAvailableException, if the book isn't available
     */
    public abstract void reserv(Book book, Subscriber applicantSub) throws NotAvailableException;

    /**
     * Allow a sub to borrow the Doc
     * @throws NotAvailableException if document isn't available
     * @param book, the book to borrow
     * @param applicantSub, sub who want to borrow
     */
    public abstract void borrow(Book book, Subscriber applicantSub) throws NotAvailableException;

    /**
     * Bring back the book
     * @param book, the book to bring back
     */
    public abstract void back(Book book);
}
