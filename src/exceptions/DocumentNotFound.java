package exceptions;

public class DocumentNotFound extends Throwable {
    public DocumentNotFound(int num) {
        super("[error] " + String.format("Document n° %d was not found !", num));
    }
}
