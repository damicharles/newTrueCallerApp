package africa.semicolon.newTrueCallerApp.exceptions;

public class UserExistsException extends Throwable {
    public UserExistsException() {
        super();
    }
    public UserExistsException(String message) {
        super(message);
    }
    public UserExistsException(Throwable err) {
        super(err);
    }
    public UserExistsException(String message, Throwable err) {
        super(message, err);
    }
}
