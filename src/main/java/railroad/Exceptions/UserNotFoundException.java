package railroad.Exceptions;

public class UserNotFoundException extends ApiException {

    public UserNotFoundException() {
        super("USER_NOT_FOUND", "User doesn't exist!");
    }
}

