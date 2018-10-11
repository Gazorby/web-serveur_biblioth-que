package services;

import exception.NotAvailableException;
import library.Book;

import java.util.Timer;
import java.util.TimerTask;

public class EndReservation extends TimerTask {

    private Book book;
    private Timer t;

    public EndReservation(Book book, Timer t) {
        this.book = book;
        this.t = t;
    }

    @Override
    public void run() {
        try {
            book.back(book.getSubscriber());
            t.cancel();
        } catch (NotAvailableException e) {
        }
    }
}
