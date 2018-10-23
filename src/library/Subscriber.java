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

    public String getMail() {
        return mail;
    }

    public boolean isAllowed() {
        if (allowed == false) {
            GregorianCalendar now = new GregorianCalendar();
            GregorianCalendar tmp = (GregorianCalendar) this.banDate.clone();
            tmp.set(Calendar.MONTH, this.banDate.get(Calendar.MONTH) + 1);
            now.setTime(new Date());

            long diff = now.getTimeInMillis() - this.banDate.getTimeInMillis();
            long diff2 = Math.abs(this.banDate.getTimeInMillis() - tmp.getTimeInMillis());
            //System.out.println("Diff = " + diff);
            //System.out.println("Diff2 = " + diff2);
            //System.out.println(now.get(Calendar.YEAR) + "/" + now.get(Calendar.MONTH) + "/" + now.get(Calendar.DAY_OF_MONTH));
            //System.out.println(tmp.get(Calendar.YEAR) + "/" + tmp.get(Calendar.MONTH) + "/" + tmp.get(Calendar.DAY_OF_MONTH));
            //System.out.println(this.banDate.get(Calendar.YEAR) + "/" + this.banDate.get(Calendar.MONTH) + "/" + this.banDate.get(Calendar.DAY_OF_MONTH));
            if (diff < diff2)
                return false;
            else {
                allowed = true;
                return true;
            }
        }
        else
            return true;
    }

    public void setAllowed(boolean allowed) {
        this.allowed = allowed;
    }

    public GregorianCalendar getBanDate() {
        return banDate;
    }

    public void setBanDate(GregorianCalendar banDate) {
        this.banDate = banDate;
    }

    public int getNum() {
        return num;
    }

    public void updateStatus() {
        this.banDate = new GregorianCalendar();
        this.allowed = false;
    }
}
