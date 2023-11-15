package christmas;

public class GiftProvider {
    public static String getChampagneMessage(int totalOrderAmount){
        if(totalOrderAmount >= Discount.CHAMPAGNE_GIFT_THRESHOLD.getPrice()){
            return "샴페인 1개";
        }
        return "없음";
    }
}
