package state;

import exception.NotAvailableException;
import library.Book;
import library.Subscriber;

public class Available extends State {
    @Override
    public void reserv(Book book) {
        book.setState(new Reserved());
    }

    @Override
    public void borrow(Book book, Subscriber subscriber) {
        book.setState(new Borrowed());
    }

    @Override
    public void back(Book book) throws NotAvailableException {
        throw new NotAvailableException("Document already available");
    }
}
