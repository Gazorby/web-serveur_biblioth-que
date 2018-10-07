package state;

import exception.NotAvailableException;
import library.GenericDocument;
import library.Subscriber;

public abstract class State {

    public abstract void  reserv(GenericDocument document) throws NotAvailableException;

    /**
     * Allow a newSub to borrow the Doc
     * @param newSub
     * @throws NotAvailableException
     */
    public abstract void borrow(GenericDocument document, Subscriber newSub) throws NotAvailableException;

    /**
     * Allow a sub to give back the Doc
     * @param sub
     */
    public abstract void back(GenericDocument document) throws NotAvailableException;
}
