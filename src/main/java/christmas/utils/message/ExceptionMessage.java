package christmas.utils.message;

public enum ExceptionMessage {
    MESSAGE("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");

    private final String error;

    ExceptionMessage(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
