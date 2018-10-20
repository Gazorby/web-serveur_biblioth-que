package state;

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
    public void back(Book book) {
        book.setState(new Available());
    }
}
