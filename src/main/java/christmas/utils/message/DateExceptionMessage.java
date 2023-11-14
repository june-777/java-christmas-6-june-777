package christmas.utils.message;

public enum DateExceptionMessage {

    MESSAGE("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");

    private final String error;

    DateExceptionMessage(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
