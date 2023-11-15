package christmas.dto.response;

public class FreeGiftResponse {

    private static final String EMPTY = "없음";
    private final String name;
    private final int benefitPrice;
    private final int count;

    public FreeGiftResponse(String name, int benefitPrice, int count) {
        this.name = name;
        this.benefitPrice = benefitPrice;
        this.count = count;
    }

    public boolean isEmpty() {
        return name == null || name.equals(EMPTY);
    }

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }

    public int getBenefitPrice() {
        return benefitPrice;
    }

    @Override
    public String toString() {
        return "FreeGiftResponse{" +
                "name='" + name + '\'' +
                ", benefitPrice=" + benefitPrice +
                ", count=" + count +
                '}';
    }
}
