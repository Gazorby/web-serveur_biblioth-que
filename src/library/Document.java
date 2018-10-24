package library;

import exceptions.NotAvailableException;

public interface Document {

    /**
     * Get the Doc number
     * @return document id
     */
    int getNum();

    /**
     * Allow a sub to reserv the Doc
     * @param sub the sub who want to reserv
     * @throws NotAvailableException if the document is not available
     */
    void reserv(Subscriber sub) throws NotAvailableException;

    /**
     * Allow a sub to borrow the Doc
     * @param sub who want to borrow
     * @throws NotAvailableException if document isn't available
     */
    void borrow(Subscriber sub) throws NotAvailableException;

    /**
     * Allow a sub to give back the Doc
     */
    void back();
}
