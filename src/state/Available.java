package state;

import exception.NotAvailableException;
import library.Book;
import library.Subscriber;

public class Available extends State {
    @Override
    public void reserv(Subscriber subscriber, Book book) {
        book.setState(new Reserved());
        this.subscriber = subscriber;
    }

    @Override
    public void borrow(Subscriber subscriber, Book book) {
        book.setState(new Borrowed());
        this.subscriber = subscriber;
    }

    @Override
    public void back(Book book) throws NotAvailableException {
        throw new NotAvailableException("Document already available");
    }
}
