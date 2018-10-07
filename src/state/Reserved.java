package state;

import exception.NotAvailableException;
import library.GenericDocument;
import library.Subscriber;

public class Reserved extends State {
    @Override
    public void reserv(GenericDocument document) throws NotAvailableException {
        throw new NotAvailableException();
    }

    @Override
    public void borrow(GenericDocument document, Subscriber newSub) throws NotAvailableException {

        if (document.getState().getClass() == Reserved.class && document.getSubscriber() == newSub) {
            document.setState(new Borrowed());
        }

        else {
            throw new NotAvailableException();
        }
    }

    @Override
    public void back(GenericDocument document) throws NotAvailableException {
        throw new NotAvailableException();
    }
}
