package library;

import app.Bitch;
import app.Subscriber;

public class LittleBitch implements Bitch {

    private int num;

    public LittleBitch(int num) {
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
