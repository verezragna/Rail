package railroad.Exceptions;

public class InternalServerErrorException extends ApiException {

    public InternalServerErrorException() {
        super("INTERNAL_SERVER_ERROR", "Server doesn't response, wait a bit please!");
    }

}
