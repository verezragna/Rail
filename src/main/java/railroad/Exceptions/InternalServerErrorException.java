package railroad.Exceptions;

public class InternalServerErrorException extends ApiException {

    public InternalServerErrorException(Exception ex) {
        super("INTERNAL_SERVER_ERROR", ex.getMessage());
    }

}
