package christmas;

import java.lang.String;

public enum Badge {
    NONE("없음", 0),
    STAR("별", 5000),
    TREE("트리", 10000),
    SANTA("산타", 20000);

    private final String badgeName;
    private final int threshold;

    Badge(String badgeName, int threshold) {
        this.badgeName = badgeName;
        this.threshold = threshold;
    }

    public static Badge getBadgeByDiscount(int totalDiscount) {
        if(totalDiscount >= SANTA.threshold){
            return SANTA;
        }
        if(totalDiscount >= TREE.threshold){
            return TREE;
        }
        if(totalDiscount >= STAR.threshold){
            return STAR;
        }
        return NONE;
    }

    public String getBadgeName() {
        return badgeName;
    }

}
