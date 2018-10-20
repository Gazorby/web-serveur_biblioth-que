package state;

import exceptions.AlreadyReserved;
import library.Book;
import library.Subscriber;

public class Reserved extends State {
    @Override
    public void reserv(Book book) throws AlreadyReserved {
        throw new AlreadyReserved(book, book.getSubscriber());
    }

    @Override
    public void borrow(Book book, Subscriber subscriber) throws AlreadyReserved {

        if (subscriber.getNum() == book.getSub().getNum()) {
            book.setState(new Borrowed());
        }

        else {
            throw new AlreadyReserved(book, book.getSubscriber());
        }
    }

    @Override
    public void back(Book book) {
        book.setState(new Available());
    }
}
