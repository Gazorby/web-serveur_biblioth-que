package library;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Subscriber {

    private int num;
    private String mail;
    private boolean allowed;
    private GregorianCalendar banDate;

    public Subscriber(int num, String mail) {
        this.num = num;
        this.mail = mail;
        this.allowed = true;
    }

    /**
     * Return subscriber's mail
     * @return a String representing the subscriber's mail
     */
    String getMail() {
        return mail;
    }

    /**
     * Check if a subscriber is allowed to borrow a doc
     * @return a boolean set to true if the subscriber is allowed, false otherwise
     */
    boolean isAllowed() {
        if (!allowed) {
            GregorianCalendar now = new GregorianCalendar();
            GregorianCalendar tmp = (GregorianCalendar) this.banDate.clone();

            tmp.set(Calendar.MONTH, this.banDate.get(Calendar.MONTH) + 1);
            now.setTime(new Date());

            long diff = now.getTimeInMillis() - this.banDate.getTimeInMillis();
            long diff2 = Math.abs(this.banDate.getTimeInMillis() - tmp.getTimeInMillis());

            if (diff < diff2) {
                return false;
            }

            else {
                allowed = true;
                return true;
            }
        }
        else {
            return true;
        }
    }

    /**
     * Return the subscriber ID
     * @return an int representing the subscriber ID
     */
    public int getNum() {
        return num;
    }

    /**
     * Set the ban date of the subscriber and update his status accordingly
     */
    public void updateStatus() {
        this.banDate = new GregorianCalendar();
        this.allowed = false;
    }
}
