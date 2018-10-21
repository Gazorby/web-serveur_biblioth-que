package exceptions;

import library.Document;
import library.Subscriber;

public class AlreadyBorrowed extends NotAvailableException {
    public AlreadyBorrowed(Document document, Subscriber subscriber, Subscriber applicantSub) {

        super(String.format("Document n° %d is already borrowed by subscriber n° %d", document.getNum(), subscriber.getNum()) +
                " (Subscriber n° " + applicantSub.getNum() + " will be notified by email when the doc will be back)");
    }
}
