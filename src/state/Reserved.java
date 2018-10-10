package state;

import exception.NotAvailableException;
import library.Book;
import library.Subscriber;

public class Reserved extends State {
    @Override
    public void reserv(Book book) throws NotAvailableException {
        throw new NotAvailableException("Document already reserved");
    }

    @Override
    public void borrow(Book book, Subscriber subscriber) throws NotAvailableException {

        if (subscriber.getNum() == book.getSub().getNum()) {
            book.setState(new Borrowed());
        }

        else {
            throw new NotAvailableException("Document is already reserved");
        }
    }

    @Override
    public void back(Book book) {
        book.setState(new Available());
    }
}
