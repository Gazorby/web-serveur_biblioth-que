package state;

import exceptions.AlreadyBorrowed;
import library.Book;
import library.Subscriber;

public class Borrowed extends State {

    @Override
    public void reserv(Book book) throws AlreadyBorrowed {
        throw new AlreadyBorrowed(book, book.getSubscriber());
    }

    @Override
    public void borrow(Book book, Subscriber subscriber) throws AlreadyBorrowed {
        throw new AlreadyBorrowed(book,  book.getSubscriber());
    }

    @Override
    public void back(Book book) {
        book.setState(new Available());
    }
}
