package christmas.dto.response;

public class BadgeResponse {

    private static final String EMPTY = "없음";
    private final String badgeName;

    public BadgeResponse(String badgeName) {
        this.badgeName = badgeName;
    }

    public boolean isEmpty() {
        return badgeName == null || badgeName.equals(EMPTY);
    }

    public String getBadgeName() {
        return badgeName;
    }
}