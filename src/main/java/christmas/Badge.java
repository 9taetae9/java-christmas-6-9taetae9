package christmas;

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


    public int getThreshold() {
        return threshold;
    }

    public static String getBadgeByDiscount(int totalDiscount) {
        Badge awaredBadge = Badge.NONE;
        for (Badge badge : Badge.values()) {
            if (totalDiscount >= badge.getThreshold()) {
                awaredBadge = badge;
                continue;
            } break;
        }
        return awaredBadge.badgeName;
    }
}
