package state;

import exceptions.AlreadyBorrowed;
import library.Book;
import library.Subscriber;

public class Borrowed extends State {

    @Override
    public void reserv(Book book, Subscriber applicantSub) throws AlreadyBorrowed {
        throw new AlreadyBorrowed(book, book.getSubscriber(), applicantSub);
    }

    @Override
    public void borrow(Book book, Subscriber applicantSub) throws AlreadyBorrowed {
        throw new AlreadyBorrowed(book,  book.getSubscriber(), applicantSub);
    }

    @Override
    public void back(Book book) {
        book.setState(new Available());
    }
}
