import java.text.NumberFormat;
import java.util.Calendar;

public class TravelCost {
    public static final int MIN_UNIT_PRICE = 1;

    public static final int MAX_UNIT_PRICE = 100;

    public static final float MIN_TRAVEL_DIST = 0.01F;

    public static final float MAX_TRAVEL_DIST = 100.0f;

    public static void main(String[] args) {
        int unitPrice = Integer.parseInt(args[0]);
        float travelDist = Float.parseFloat(args[1]);
        if (unitPrice < MIN_UNIT_PRICE || unitPrice > MAX_UNIT_PRICE)
            System.out.println("The price that can be entered is 1 to 100");
        if (travelDist < MIN_TRAVEL_DIST || travelDist > MAX_TRAVEL_DIST)
            System.out.println("The distnce that can be entered is 0.01 to 100.00");
        if (unitPrice < MIN_UNIT_PRICE || unitPrice > MAX_UNIT_PRICE || travelDist < MIN_TRAVEL_DIST || travelDist > MAX_TRAVEL_DIST) return;
        Calendar currentMonth = Calendar.getInstance();
        currentMonth.set(Calendar.DATE, 1);
        Calendar nextSun = (Calendar)currentMonth.clone(), nextSat = (Calendar)currentMonth.clone();
        nextSun.add(Calendar.DATE, 7 - currentMonth.get(Calendar.DAY_OF_WEEK));
        nextSat.add(Calendar.DATE, 8 - currentMonth.get(Calendar.DAY_OF_WEEK));
        int businessDays = currentMonth.getActualMaximum(Calendar.DAY_OF_MONTH)
            - nextSun.getActualMaximum(Calendar.DAY_OF_WEEK_IN_MONTH)
            - nextSat.getActualMaximum(Calendar.DAY_OF_WEEK_IN_MONTH);
        int pricePerDay = (int)(unitPrice * travelDist), pricePerMonth = (int)(pricePerDay * businessDays);
        int taxExempt = Math.min(pricePerMonth,
            travelDist < 4.0F ? 0
            : travelDist < 20.0F ? 4200
            : travelDist < 30.0F ? 7100
            : travelDist < 50.0F ? 12900
            : travelDist < 70.0F ? 18700
            : travelDist < 90.0F ? 24400
            : travelDist < 110.0F ? 28000 : 31600
        );
        NumberFormat ni = NumberFormat.getNumberInstance();
        System.out.println("距離単価：" + ni.format(unitPrice) + "円");
        System.out.println("往復距離：" + ni.format(travelDist) + "km");
        System.out.println("営業日：" + ni.format(businessDays) + "日");
        System.out.println("１日あたり交通費：" + ni.format(pricePerDay) + "円");
        System.out.println("１ヶ月交通費：" + ni.format(pricePerMonth) + "円");
        System.out.println("課税額：" + ni.format(pricePerMonth - taxExempt) + "円");
        System.out.println("非課税額：" + ni.format(taxExempt) + "円");
    }
}