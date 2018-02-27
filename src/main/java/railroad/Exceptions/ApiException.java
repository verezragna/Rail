package railroad.Exceptions;

public abstract class ApiException extends RuntimeException {

    private final String code;
    private final String message;

    ApiException(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
