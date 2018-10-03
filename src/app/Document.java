package app;

import exception.NotAvailableException;

public interface Document {

    int getNum();
    void reserv(Subscriber sub) throws NotAvailableException;
    void borrow(Subscriber sub) throws NotAvailableException;
    void back(Subscriber sub);
}
