package christmas;

public enum Badge {
    NONE("없음", 0),
    STAR("별", 5000),
    TREE("트리", 10000),
    SANTA("산타", 20000);

    private String badgeName;
    private int threshold

    Badge(String badgeName, int threshold){
        this.badgeName = badgeName;
        this.threshold = threshold;
    }

    public String getBadgeName(){
        return badgeName;
    }

    public int getThreshold(){
        return threshold;
    }

    public static String getBadgeNameByDiscount(int totalDiscount){
        Badge badge = Badge.NONE;
        for(Badge b : Badge.values()){
            if(totalDiscount>=b.getThreshold()){
                badge = b;
            }
        }
        return badge.getBadgeName();
    }
}
