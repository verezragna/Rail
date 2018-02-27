package railroad.Exceptions;

public class UserAlreadyExistsException extends ApiException {

    public UserAlreadyExistsException() {
        super("USER_ALREADY_EXISTS", "Логин уже занят!");
    }

}