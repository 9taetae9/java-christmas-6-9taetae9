package christmas;

import java.util.List;

public class DiscountCalculator {
   // 할인 계산을 위한 메서드
   public static int calculateTotalDiscount(int date, List<Order> orders) {
      boolean isWeekend = isWeekend(date);
      boolean isSpecialDay = isSpecialDay(date);
      int totalDiscount = 0;

      // 크리스마스 디데이 할인 계산
      if (date >= 1 && date <= 25) {
         totalDiscount += calculateChristmasDiscount(date);
      }

      // 주문별 할인 계산
      for (Order order : orders) {
         totalDiscount += calculateOrderDiscount(order, date, isWeekend, isSpecialDay);
      }

      return totalDiscount;
   }

   // 크리스마스 디데이 할인 계산을 위한 메서드
   private static int calculateChristmasDiscount(int day) {
      return Discount.CHRISTMAS_DISCOUNT_START.getPrice() + (day - 1) * Discount.DAILY_INCREMENT.getPrice();
   }

    // 주문별 할인 계산을 위한 메서드
   private static int calculateOrderDiscount(Order order, int date, boolean isWeekend, boolean isSpecialDay) {
      int orderDiscount = 0;

      //평일 디저트 할인
      if (!isWeekend && order.getCategory().equals("Dessert")) {
         orderDiscount += Discount.WEEKDAY_DESSERT_DISCOUNT.getPrice() * order.getQuantity();
      }

      //주말 메인 할인
      if (isWeekend && order.getCategory().equals("Main")) {
         orderDiscount += Discount.WEEKEND_MAIN_DISCOUNT.getPrice() * order.getQuantity();
      }

      //스페셜 할인
      if (isSpecialDay) {
         orderDiscount += Discount.SPECIAL_DISCOUNT.getPrice();
      }

      return orderDiscount;
   }

   public static boolean isChampagne(int totalOrderAmount) {
      return totalOrderAmount >= Discount.THRESHOLD_FOR_CHAMPAGNE.getPrice();
   }

   private static boolean isWeekend(int date) {
      return date % 7 == 1 || date % 7 == 2;
   }

   private static boolean isSpecialDay(int data){
      List<Integer> specialDays = List.of(3,10,17,24,25,31);
      return specialDays.contains(data);
   }
}