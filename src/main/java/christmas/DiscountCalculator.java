package christmas;

import java.util.List;
import java.util.Set;

public class DiscountCalculator {
   // 할인 계산을 위한 메서드
   public static int calculateTotalDiscount(int date, List<Order> orders) {
      boolean isWeekend = isWeekend(date);
      boolean isSpecialDay = isSpecialDay(date);
      int totalDiscount = 0;

      // 크리스마스 디데이 할인 계산
      totalDiscount += calculateChristmasDiscount(date);


      // 주문별 할인 계산
      for (Order order : orders) {
         totalDiscount += calculateOrderDiscount(order, date);
      }

      return totalDiscount;
   }

   public static int calculateFinalPayment(int totalOrderAmount, int totalDiscount) {
      return totalOrderAmount - (totalDiscount - champagneDiscount(totalOrderAmount));
   }

   // 크리스마스 디데이 할인 계산을 위한 메서드
   private static int calculateChristmasDiscount(int date) {
      if (date >= 1 && date <= 25) {
         return Discount.CHRISTMAS_DISCOUNT_START.getPrice() + (date - 1) * Discount.DAILY_INCREMENT.getPrice();
      } return 0;
   }

    // 주문별 할인 계산을 위한 메서드
   private static int calculateOrderDiscount(Order order, int date) {
      int orderDiscount = 0;
      boolean isWeekend = isWeekend(date);
      boolean isSpecialDay = isSpecialDay(date);

      //평일 디저트 할인
      if (!isWeekend && "Dessert".equals(order.getMenuCategory())) {
         orderDiscount += Discount.WEEKDAY_DESSERT_DISCOUNT.getPrice() * order.getMenuQuantity();
      }

      //주말 메인 할인
      if (isWeekend && "Main".equals(order.getMenuCategory())){
         orderDiscount += Discount.WEEKEND_MAIN_DISCOUNT.getPrice() * order.getMenuQuantity();
      }

      //스페셜 할인
      if (isSpecialDay) {
         orderDiscount += Discount.SPECIAL_DISCOUNT.getPrice();
      }

      return orderDiscount;
   }

   private static int totalOrderAmount(List<Order> orders) {
      return orders.stream().mapToInt(Order::getTotalPrice).sum();
   }

   private static int champagneDiscount(int totalOrderAmount) {
      if(totalOrderAmount >= Discount.CHAMPAGNE_GIFT_THRESHOLD.getPrice()){
         return Discount.CHAMPAGNE_VALUE.getPrice();
      } return 0;
   }

   private static boolean isWeekend(int date) {
      return date % 7 == 1 || date % 7 == 2;
   }

   private static boolean isSpecialDay(int data){
      Set<Integer> specialDays = Set.of(3,10,17,24,25,31);
      return specialDays.contains(data);
   }
}