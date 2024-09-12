package a02;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ClosingDate {
    public static void main(String[] args) {
        LocalDate currentDate = LocalDate.now();
        System.out.println("現在日付: " + currentDate.format(DateTimeFormatter.ofPattern("yyyy年MM月dd日")));

        LocalDate endOfCurrentPeriod;

        if (currentDate.getDayOfMonth() > 20) {
            endOfCurrentPeriod = currentDate.plusMonths(1).withDayOfMonth(20);
        } else {
            endOfCurrentPeriod = currentDate.withDayOfMonth(20);
        }

        String periodYearMonth = endOfCurrentPeriod.format(DateTimeFormatter.ofPattern("yyyy年MM月度"));
        System.out.println("年月度: " + periodYearMonth);
    }
}

