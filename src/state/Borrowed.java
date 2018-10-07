package state;

import exception.NotAvailableException;
import library.GenericDocument;
import library.Subscriber;

public class Borrowed extends State {
    @Override
    public void reserv(GenericDocument document) throws NotAvailableException {
        throw new NotAvailableException();
    }

    @Override
    public void borrow(GenericDocument document, Subscriber newSub) throws NotAvailableException {
        throw new NotAvailableException();
    }

    @Override
    public void back(GenericDocument document) {
        document.setState(new Available());
    }
}
