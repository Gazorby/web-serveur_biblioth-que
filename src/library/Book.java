package library;

import exceptions.NotAvailableException;
import exceptions.SubNotAllowed;
import state.Available;
import state.State;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Book implements Document {

    private int num;
    private State state;
    private Subscriber sub;
    private GregorianCalendar borrowDate;

    public Book(int num) {
        this.num = num;
        state = new Available();
    }

    @Override
    public int getNum() {
        return this.num;
    }

    /**
     * Reserv this book to the sub given
     * @param sub the sub who want to reserv
     * @throws NotAvailableException if book isn't available
     */
    @Override
    public synchronized void reserv(Subscriber sub) throws NotAvailableException {
        if (sub.isAllowed()) {
            state.reserv(this, sub);
            this.sub = sub;
        }
        else throw new SubNotAllowed(sub);
    }

    /**
     * Allow sub given in parameter to borrow this book
     * @param sub who want to borrow
     * @throws NotAvailableException if this book isn't available
     */
    @Override
    public synchronized void borrow(Subscriber sub) throws NotAvailableException {
        if (sub.isAllowed()) {
            state.borrow(this, sub);
            this.sub = sub;
            this.borrowDate = new GregorianCalendar();
        }
        else throw new SubNotAllowed(sub);
    }

    /**
     * Bring back the document. Change the state accordingly
     */
    @Override
    public synchronized void back() {
        state.back(this);

        if (lateReturnCheck()) {
            this.sub.updateStatus();
        }
    }

    /**
     * Set the state of the book
     * @param state, the {@link State} we want the book to be
     */
    public void setState(State state) {
        this.state = state;
    }

    /**
     * Get the subscriber associated with this book
     * @return, A {@link Subscriber}, associated with the book
     */
    public Subscriber getSubscriber() {
        return sub;
    }

    /**
     * If the subscriber associated with this book borrowed it,
     * this check if the subscriber is delaying bringing it back,
     * @return true if the subscriber is late, false otherwise
     */
    private boolean lateReturnCheck() {
        GregorianCalendar  now = new GregorianCalendar();
        GregorianCalendar tmp = (GregorianCalendar) this.borrowDate.clone();
        tmp.set(Calendar.DAY_OF_MONTH, this.borrowDate.get(Calendar.DAY_OF_MONTH) + 14);
        now.setTime(new Date());

        long diff = now.getTimeInMillis() - this.borrowDate.getTimeInMillis();
        long diff2 = Math.abs(this.borrowDate.getTimeInMillis() - tmp.getTimeInMillis());

        return diff >= diff2;
    }
}
