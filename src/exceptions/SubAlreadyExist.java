package exceptions;

public class SubAlreadyExist extends Throwable {
    public SubAlreadyExist(int num) {
        super("[error] Sub n° " + num + " already exist");
    }
}
