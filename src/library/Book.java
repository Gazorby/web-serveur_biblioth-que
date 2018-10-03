package library;

import app.Document;
import app.Subscriber;

public class Book implements Document {

    private int num;

    public Book(int num) {
        this.num = num;
    }

    @Override
    public int getNum() {
        return num;
    }

    @Override
    public void reserv(Subscriber sub) {

    }

    @Override
    public void borrow(Subscriber sub) {

    }

    @Override
    public void back(Subscriber sub) {

    }
}
