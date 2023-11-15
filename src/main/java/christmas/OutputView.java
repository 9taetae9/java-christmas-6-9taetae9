package christmas;

import java.util.List;

import static christmas.DiscountCalculator.calculateTotalDiscountWithoutChampagne;

public class OutputView {
    public static void greeting(){
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플레너입니다.");
    }

    public static void printMenu(List<Order> orders){
        System.out.println("<주문 메뉴>");
        for(Order order : orders){
            System.out.println(order.getMenuName() + " " + order.getMenuQuantity() + "개");
        }
        System.out.println();
    }
    private static String getDiscountDetailsMessage(int totalDiscount) {
        if (totalDiscount > 0) {
            return "크리스마스 디데이 할인: -" + totalDiscount + "원";
        }
        return "없음";
    }


    public static void printOrderDetails(int date, List<Order> orders) {
        int totalOrderAmount = DiscountCalculator.totalOrderAmount(orders);
        int totalDiscountExcludingChampagne = calculateTotalDiscountWithoutChampagne(date, orders);
        int finalPayment = totalOrderAmount - totalDiscountExcludingChampagne;
        int totalDiscountIncludingChampagne = calculateTotalDiscountIncludingChampagne(date, orders);
        Badge badge = Badge.getBadgeByDiscount(totalDiscountIncludingChampagne);

        String discountDetails = DiscountCalculator.constructDiscountDetails(date, orders);

        System.out.println("<할인 전 총주문 금액>\n" + formatCurrency(totalOrderAmount)+'\n');
        System.out.println("<증정 메뉴>\n" + (DiscountCalculator.qualifiesForChampagne(totalOrderAmount) ? "샴페인 1개\n" : "없음\n"));
        System.out.println("<혜택 내역>");
        System.out.println(discountDetails+'\n');
        System.out.println("<총혜택 금액>\n" + formatCurrency(-totalDiscountIncludingChampagne)+'\n');
        System.out.println("<할인 후 예상 결제 금액>\n" + formatCurrency(finalPayment)+'\n');
        System.out.println("<12월 이벤트 배지>\n" + badge.getBadgeName());
    }

    private static int calculateTotalDiscountIncludingChampagne(int date, List<Order> orders) {
        return calculateTotalDiscountWithoutChampagne(date, orders) +
                (DiscountCalculator.qualifiesForChampagne(DiscountCalculator.totalOrderAmount(orders)) ? Discount.CHAMPAGNE_VALUE.getPrice() : 0);
    }

    private static String formatCurrency(int amount) {
        return String.format("%,d원", amount);
    }

}
