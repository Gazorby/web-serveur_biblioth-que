package state;

import exception.NotAvailableException;
import library.Book;
import library.GenericDocument;
import library.Subscriber;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AvailableTest {

    @Test
    void available() {
        Subscriber subscriber = new Subscriber(1);
        GenericDocument book = new Book(1);
        assertEquals(Available.class, book.getState().getClass());
    }

    @Test
    void reserv() throws NotAvailableException {
        Subscriber subscriber = new Subscriber(1);
        Subscriber subscriber2 = new Subscriber(2);
        GenericDocument book = new Book(1);

        book.reserv(subscriber);
        assertEquals(subscriber.getNum(), book.getSubscriber().getNum());
        book.borrow(subscriber2);
        assertEquals(subscriber.getNum(), book.getSubscriber().getNum());
        book.borrow(subscriber);
        assertEquals(Borrowed.class, book.getState().getClass());
    }

    @Test
    void borrow() throws NotAvailableException {
        Subscriber subscriber = new Subscriber(1);
        Subscriber subscriber2 = new Subscriber(2);
        GenericDocument book = new Book(1);

        book.borrow(subscriber);
        assertEquals(Borrowed.class, book.getState().getClass());
        assertEquals(subscriber.getNum(), book.getSubscriber().getNum());
        book.borrow(subscriber2);
        assertEquals(subscriber.getNum(), book.getSubscriber().getNum());
    }

    @Test
    void back() throws NotAvailableException {
        Subscriber subscriber = new Subscriber(1);
        GenericDocument book = new Book(1);

        book.borrow(subscriber);
        assertEquals(Borrowed.class, book.getState().getClass());
        assertEquals(subscriber.getNum(), book.getSubscriber().getNum());
        book.back(subscriber);
        assertEquals(Available.class, book.getState().getClass());
    }
}