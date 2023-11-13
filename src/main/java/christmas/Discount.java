package christmas;

public enum Discount {
    CHRISTMAS_DISCOUNT_START(1000),
    DAILY_INCREMENT(100),
    WEEKDAY_DESSERT_DISCOUNT(2023),
    WEEKEND_MAIN_DISCOUNT(2023),
    SPECIAL_DISCOUNT(1000),
    CHAMPAGNE_VALUE(25000),
    THRESHOLD_FOR_CHAMPAGNE(120000);

    private final int value;

    Discount(int value){
        this.value = value;
    }

    public int getValue(){
        return value;
    }
}
