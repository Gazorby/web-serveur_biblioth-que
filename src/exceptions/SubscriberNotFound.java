package exceptions;

public class SubscriberNotFound extends Throwable {
    public SubscriberNotFound(int num) {
        super("[error] " + String.format("Subscriber n° %d was not found !", num));
    }
}
