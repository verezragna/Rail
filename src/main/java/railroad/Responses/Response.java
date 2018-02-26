package railroad.Responses;

public abstract class Response {
    private boolean success;

    Response(Boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }
}
