package railroad.Responses;

public class BaseResponse {

    protected final Boolean success;

    public BaseResponse(Boolean success) {
        this.success = success;
    }

    public Boolean getSuccess() {
        return success;
    }

}