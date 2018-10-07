package library;

import exception.NotAvailableException;

public class Book extends GenericDocument {


    public Book(int num) {
        super(num);
    }

    @Override
    public void reserv(Subscriber sub) {
        try {
            this.getState().reserv(this);
            setSubscriber(sub);
        } catch (NotAvailableException e) {

        }
    }

    @Override
    public void borrow(Subscriber sub) {
        try {
            this.getState().borrow(this, sub);

        } catch (NotAvailableException e) {
        }
    }

    @Override
    public void back(Subscriber sub) {
        try {
            this.getState().back(this);
            setSubscriber(null);
        } catch (NotAvailableException e) {
        }
    }
}
