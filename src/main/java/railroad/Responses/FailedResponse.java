package railroad.Responses;

public class FailedResponse extends Response {
    private String status;

    public FailedResponse(String exceptionMessage){
        super(false);
        this.status = exceptionMessage;

    }

    public String getStatus() {
        return status;
    }
}
