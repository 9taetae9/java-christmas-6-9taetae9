package christmas;

public enum Discount {
    CHRISTMAS_DISCOUNT_START(1000),
    DAILY_INCREMENT(100),
    WEEKDAY_DESSERT_DISCOUNT(2023),
    WEEKEND_MAIN_DISCOUNT(2023),
    SPECIAL_DISCOUNT(1000),
    CHAMPAGNE_VALUE(25000),
    CHAMPAGNE_GIFT_THRESHOLD(120000);

    private final int price;

    Discount(int price){
        this.price = price;
    }

    public int getPrice(){
        return price;
    }
}
