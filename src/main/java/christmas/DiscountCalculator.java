package christmas;

import java.util.List;
import java.util.Set;

public class DiscountCalculator {
   public static int calculateTotalDiscount(int date, List<Order> orders) {
      boolean isWeekend = isWeekend(date);
      boolean isSpecialDay = isSpecialDay(date);
      int totalDiscount = 0;

      // 크리스마스 디데이 할인 계산
      totalDiscount += calculateChristmasDiscount(date);


      // 주문별 할인 계산
      for (Order order : orders) {
         totalDiscount += calculateOrderDiscount(date, order);
      }

      return totalDiscount;
   }

   public static int calculateFinalPayment(int totalOrderAmount, int totalDiscountWithoutChampagne) {
      return totalOrderAmount - totalDiscountWithoutChampagne;
   }

   public static int calculateTotalDiscountWithoutChampagne(int date, List<Order> orders) {
      int totalDiscount = 0;
      for (Order order : orders) {
         totalDiscount += calculateOrderDiscount(date, order);
      }
      totalDiscount += calculateChristmasDiscount(date);
      // Special discount should be added only once
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
      int totalDiscount = 0;

      int christmasDiscount = calculateChristmasDiscount(date);
      if (christmasDiscount > 0) {
         addDiscountDetail(discountDetails, "크리스마스 디데이 할인", christmasDiscount);
         totalDiscount += christmasDiscount;
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
         totalDiscount += weekdayDiscountTotal;
      }
      if (weekendDiscountTotal > 0) {
         addDiscountDetail(discountDetails, "주말 할인", weekendDiscountTotal);
         totalDiscount += weekendDiscountTotal;
      }

      // Special discount applies only once regardless of the number of items
      if (isSpecialDay(date)) {
         int specialDiscount = Discount.SPECIAL_DISCOUNT.getPrice();
         addDiscountDetail(discountDetails, "특별 할인", specialDiscount);
         totalDiscount += specialDiscount;
      }

      // Champagne discount is shown in details but not added to totalDiscount
      if (qualifiesForChampagne(totalOrderAmount(orders))) {
         addDiscountDetail(discountDetails, "증정 이벤트", Discount.CHAMPAGNE_VALUE.getPrice());
      }

      return discountDetails.length() > 0 ? discountDetails.toString().trim() : "없음";
   }

   public static int getTotalDiscountIncludingChampagne(int date, List<Order> orders) {
      return calculateTotalDiscount(date, orders) +
              (qualifiesForChampagne(totalOrderAmount(orders)) ? Discount.CHAMPAGNE_VALUE.getPrice() : 0);
   }

   private static void addDiscountDetail(StringBuilder details, String type, int amount) {
      details.append(type).append(": -").append(amount).append("원\n");
   }

   private static String getDiscountType(String category, int date) {
      if ("Main".equals(category) && isWeekend(date)) {
         return "주말 할인";
      } else if ("Dessert".equals(category) && !isWeekend(date)) {
         return "평일 할인";
      }
      return "";
   }


   public static int totalOrderAmount(List<Order> orders) {
      return orders.stream().mapToInt(Order::getTotalPrice).sum();
   }

   private static int champagneDiscount(int totalOrderAmount) {
      if(totalOrderAmount >= Discount.CHAMPAGNE_GIFT_THRESHOLD.getPrice()){
         return Discount.CHAMPAGNE_VALUE.getPrice();
      } return 0;
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