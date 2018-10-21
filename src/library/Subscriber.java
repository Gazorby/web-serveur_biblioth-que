package library;

public class Subscriber {

    private int num;
    private String mail;

    public Subscriber(int num, String mail) {
        this.num = num;
        this.mail = mail;
    }

    public int getNum() {
        return num;
    }

    public String getMail() {
        return mail;
    }
}
