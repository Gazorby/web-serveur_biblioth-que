package state;

import exception.NotAvailableException;
import library.Book;
import library.Subscriber;

public class Borrowed extends State {

    @Override
    public void reserv(Subscriber subscriber, Book book) throws NotAvailableException {
        throw new NotAvailableException("Document is borrowed");
    }

    @Override
    public void borrow(Subscriber subscriber, Book book) throws NotAvailableException {
        throw new NotAvailableException("Document already borrowed");
    }

    @Override
    public void back(Book book) {
        book.setState(new Available());
        subscriber = null;
    }
}
