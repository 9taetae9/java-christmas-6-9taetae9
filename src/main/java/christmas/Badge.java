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
        Badge awaredBadge = NONE;
        for (Badge badge : Badge.values()) {
            if (totalDiscount >= badge.threshold) {
                awaredBadge = badge;
            }
        }
        return awaredBadge;
    }

    public String getBadgeName() {
        return badgeName;
    }

    public int getThreshold() {
        return threshold;
    }
}
