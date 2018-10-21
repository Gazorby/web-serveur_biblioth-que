package exceptions;

import library.Subscriber;

public class AlreadyInMailingList extends Throwable {
    public AlreadyInMailingList(Subscriber subscriber) {
        super("[warning] Subscriber n° " + subscriber.getNum() + " is already in mailing list");
    }
}
