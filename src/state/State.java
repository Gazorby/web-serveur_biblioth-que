package state;

import exceptions.NotAvailableException;
import library.Book;
import library.Subscriber;

public interface State {

    /**
     * Try to set book state to reserved sate
     * @param book, the book to be reserved
     * @throws NotAvailableException, if the book can't be set to reserved state
     */
    void reserv(Book book, Subscriber applicantSub) throws NotAvailableException;

    /**
     * Try to set book state to borrowed state
     * @throws NotAvailableException if book can't be set to borrowed state
     * @param book, the book to borrow
     * @param applicantSub, sub who want to borrow
     */
    void borrow(Book book, Subscriber applicantSub) throws NotAvailableException;

    /**
     * Try to set book state to back sate
     * @param book, the book to bring back
     */
    void back(Book book);
}
