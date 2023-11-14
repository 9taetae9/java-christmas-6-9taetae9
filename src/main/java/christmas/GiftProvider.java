package christmas;

public class GiftProvider {
    private static final int CHAMPAGNE_GIFT_THRESHOLD = 120000;

    public static String getChampagneMessage(int totalOrderAmount){
        if(totalOrderAmount >= CHAMPAGNE_GIFT_THRESHOLD){
            return "샴페인 1개";
        }
        return "없음";
    }
}
