package state;

import exception.NotAvailableException;
import library.GenericDocument;
import library.Subscriber;

public class Available extends State {
    @Override
    public void reserv(GenericDocument document) {
        document.setState(new Reserved());
    }

    @Override
    public void borrow(GenericDocument document, Subscriber newSub) {
        document.setState(new Borrowed());
    }

    @Override
    public void back(GenericDocument document) throws NotAvailableException {
        throw new NotAvailableException();
    }
}
