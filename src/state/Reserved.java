package state;

import exception.NotAvailableException;
import library.Book;
import library.Subscriber;

public class Reserved extends State {
    @Override
    public void reserv(Subscriber subscriber, Book book) throws NotAvailableException {
        throw new NotAvailableException("Document already reserved");
    }

    @Override
    public void borrow(Subscriber subscriber, Book book) throws NotAvailableException {

        if (this.subscriber.getNum() == subscriber.getNum()) {
            book.setState(new Borrowed());
            this.subscriber = subscriber;
        }

        else {
            throw new NotAvailableException("Document is already reserved");
        }
    }

    @Override
    public void back(Book book) {
        book.setState(new Available());
        subscriber = null;
    }
}
