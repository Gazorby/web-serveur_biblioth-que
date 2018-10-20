package exceptions;

import library.Document;
import library.Subscriber;

public class AlreadyReserved extends NotAvailableException {
    public AlreadyReserved(Document document, Subscriber subscriber) {
        super(String.format("Document n° %d is already reserved by subscriber n° %d", document.getNum(), subscriber.getNum()));
    }
}
