package library;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Library {

    public static List<Subscriber> subscribers = new ArrayList<Subscriber>();
    public static Map<Integer, Book> books = new HashMap<Integer, Book>();

    static {
        subscribers.add(new Subscriber(1));
        subscribers.add(new Subscriber(2));
        subscribers.add(new Subscriber(3));
    }

    static {
        books.put(1, new Book(1));
        books.put(2, new Book(2));
        books.put(3, new Book(3));
    }
}
