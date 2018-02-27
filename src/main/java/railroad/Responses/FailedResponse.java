package railroad.Responses;

import railroad.Exceptions.ApiException;

public class FailedResponse extends BaseResponse {

    private final String code;
    private final String message;

    public FailedResponse(ApiException apiException) {
        super(false);
        this.code = apiException.getCode();
        this.message = apiException.getMessage();
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}