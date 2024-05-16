package umc.study.apiPayload.code.status;

public enum SuccessStatus {
    _OK("OK", "Operation successful");

    private final String code;
    private final String message;

    private SuccessStatus(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }
}
