package christmas.domain.event;

import christmas.domain.Menu;

public class FreeGift {
    private final Menu freeGift;
    private final int count;

    public FreeGift(Menu freeGift, int count) {
        this.freeGift = freeGift;
        this.count = count;
    }

    public int calculateBenefitPrice() {
        return freeGift.getPrice() * count * -1;
    }

    public int getCount() {
        return count;
    }

    public String getGiftName() {
        return freeGift.getName();
    }
}
