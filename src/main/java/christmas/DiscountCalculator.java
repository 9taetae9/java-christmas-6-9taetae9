package christmas;

import java.util.List;
import java.util.Set;

public class DiscountCalculator {
   public static int calculateTotalDiscount(int date, List<Order> orders) {
      int totalDiscount = 0;

      // 크리스마스 디데이 할인 계산
      totalDiscount += calculateChristmasDiscount(date);


      // 주문별 할인 계산
      for (Order order : orders) {
         totalDiscount += calculateOrderDiscount(date, order);
      }

      return totalDiscount;
   }

   public static int calculateTotalDiscountWithoutChampagne(int date, List<Order> orders) {
      int totalDiscount = 0;
      for (Order order : orders) {
         totalDiscount += calculateOrderDiscount(date, order);
      }
      totalDiscount += calculateChristmasDiscount(date);
      if (isSpecialDay(date)) {
         totalDiscount += Discount.SPECIAL_DISCOUNT.getPrice();
      }
      return totalDiscount;
   }

   private static int calculateChristmasDiscount(int date) {
      if (date >= 1 && date <= 25) {
         return Discount.CHRISTMAS_DISCOUNT_START.getPrice() + (date - 1) * Discount.DAILY_INCREMENT.getPrice();
      }
      return 0;
   }

   private static int calculateOrderDiscount(int date, Order order) {
      int discountPerItem = 0;

      if ("Dessert".equals(order.getMenuCategory()) && !isWeekend(date)) {
         discountPerItem = Discount.WEEKDAY_DESSERT_DISCOUNT.getPrice();
      } else if ("Main".equals(order.getMenuCategory()) && isWeekend(date)) {
         discountPerItem = Discount.WEEKEND_MAIN_DISCOUNT.getPrice();
      }

      return discountPerItem * order.getMenuQuantity();
   }

   public static String constructDiscountDetails(int date, List<Order> orders) {
      StringBuilder discountDetails = new StringBuilder();

      int christmasDiscount = calculateChristmasDiscount(date);
      if (christmasDiscount > 0) {
         addDiscountDetail(discountDetails, "크리스마스 디데이 할인", christmasDiscount);
      }

      int weekdayDiscountTotal = 0;
      int weekendDiscountTotal = 0;
      for (Order order : orders) {
         int orderDiscount = calculateOrderDiscount(date, order);
         if (!isWeekend(date) && "Dessert".equals(order.getMenuCategory())) {
            weekdayDiscountTotal += orderDiscount;
         } else if (isWeekend(date) && "Main".equals(order.getMenuCategory())) {
            weekendDiscountTotal += orderDiscount;
         }
      }

      if (weekdayDiscountTotal > 0) {
         addDiscountDetail(discountDetails, "평일 할인", weekdayDiscountTotal);
      }
      if (weekendDiscountTotal > 0) {
         addDiscountDetail(discountDetails, "주말 할인", weekendDiscountTotal);
      }

      if (isSpecialDay(date)) {
         int specialDiscount = Discount.SPECIAL_DISCOUNT.getPrice();
         addDiscountDetail(discountDetails, "특별 할인", specialDiscount);
      }

      if (qualifiesForChampagne(totalOrderAmount(orders))) {
         addDiscountDetail(discountDetails, "증정 이벤트", Discount.CHAMPAGNE_VALUE.getPrice());
      }

      return discountDetails.length() > 0 ? discountDetails.toString().trim() : "없음";
   }

   private static void addDiscountDetail(StringBuilder details, String type, int amount) {
      if (amount != 0) {
         String formattedAmount = String.format("%,d원", amount);
         details.append(type).append(": -").append(formattedAmount).append("\n");
      } else {
         details.append(type).append(":\n없음\n");
      }
   }


   public static int totalOrderAmount(List<Order> orders) {
      return orders.stream().mapToInt(Order::getTotalPrice).sum();
   }

   public static boolean qualifiesForChampagne(int totalOrderAmount) {
      return totalOrderAmount >= Discount.CHAMPAGNE_GIFT_THRESHOLD.getPrice();
   }

   private static boolean isWeekend(int date) {
      return date % 7 == 1 || date % 7 == 2;
   }

   private static boolean isSpecialDay(int data){
      Set<Integer> specialDays = Set.of(3,10,17,24,25,31);
      return specialDays.contains(data);
   }
}