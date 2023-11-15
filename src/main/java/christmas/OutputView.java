package christmas;

import java.util.List;

public class OutputView {
    public static void greeting(){
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플레너입니다.");
    }

    public static void printMenu(List<Order> orders){
        System.out.println("<주문 메뉴>");
        for(Order order : orders){
            System.out.println(order.getMenuName() + " " + order.getMenuQuantity() + "개");
        }
    }
    private static String getDiscountDetailsMessage(int totalDiscount) {
        if (totalDiscount > 0) {
            return "크리스마스 디데이 할인: -" + totalDiscount + "원";
        }
        return "없음";
    }


    public static void printOrderDetails(int totalOrderAmount, int totalDiscount) {
        System.out.println("<할인 전 총주문 금액>\n" + totalOrderAmount + "원");
        System.out.println("<증정 메뉴>\n" + GiftProvider.getChampagneMessage(totalOrderAmount));
        System.out.println("<혜택 내역>\n" + getDiscountDetailsMessage(totalDiscount));
        System.out.println("<총혜택 금액>\n-" + totalDiscount + "원");
        System.out.println("<할인 후 예상 결제 금액>\n" + DiscountCalculator.calculateFinalPayment(totalOrderAmount, totalDiscount) + "원");
        System.out.println("<12월 이벤트 배지>\n" + Badge.getBadgeByDiscount(totalDiscount));
    }

}
