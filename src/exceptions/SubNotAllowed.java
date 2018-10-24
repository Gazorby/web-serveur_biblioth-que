package exceptions;

import library.Subscriber;

public class SubNotAllowed extends NotAvailableException{
    public SubNotAllowed(Subscriber sub) {
        super("Subscriber " + sub.getNum() + " is'nt allowed to borrow");
    }
}
