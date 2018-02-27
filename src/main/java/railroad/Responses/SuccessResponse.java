package railroad.Responses;

public class SuccessResponse extends BaseResponse {

    private final Object result;

    public SuccessResponse(Object result) {
        super(true);
        this.result = result;
    }

    public Object getResult() {
        return result;
    }

}