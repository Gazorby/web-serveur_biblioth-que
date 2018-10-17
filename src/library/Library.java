package library;

import java.util.HashMap;
import java.util.Map;

public class Library {

    private static Map<Integer, Subscriber> subscribers = new HashMap<>();
    private static Map<Integer, Book> books = new HashMap<>();

    static {
        subscribers.put(1, new Subscriber(1));
        subscribers.put(2, new Subscriber(2));
        subscribers.put(3, new Subscriber(3));
    }

    static {
        books.put(1, new Book(1));
        books.put(2, new Book(2));
        books.put(3, new Book(3));
    }

    public static Document getDocument(int num) {
        return books.get(num);
    }

    public static Subscriber getSubscriber(int num) {
        return subscribers.get(num);
    }
}
