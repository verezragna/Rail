package railroad.Responses;

public class SuccessResponse extends Response {
    private Object status;

    public SuccessResponse(Object status){
        super(true);
        this.status = status;
    }

    public Object getStatus() {
        return status;
    }
}
