package state;

import exceptions.AlreadyReserved;
import library.Book;
import library.Subscriber;

public class Reserved implements State {

    @Override
    public void reserv(Book book, Subscriber applicantSub) throws AlreadyReserved {
        throw new AlreadyReserved(book, book.getSubscriber());
    }

    @Override
    public void borrow(Book book, Subscriber applicantSub) throws AlreadyReserved {

        if (applicantSub.getNum() == book.getSubscriber().getNum()) {
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
