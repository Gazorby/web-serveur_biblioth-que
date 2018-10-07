package library;

import exception.NotAvailableException;

public interface Document {

    /**
     * Get the Doc number
     * @return
     */
    int getNum();

    /**
     * Allow a sub to reserv the Doc
     * @param sub
     * @throws NotAvailableException
     */
    void reserv(Subscriber sub) throws NotAvailableException;

    /**
     * Allow a sub to borrow the Doc
     * @param sub
     * @throws NotAvailableException
     */
    void borrow(Subscriber sub) throws NotAvailableException;

    /**
     * Allow a sub to give back the Doc
     * @param sub
     */
    void back(Subscriber sub) throws NotAvailableException;
}
