package a05;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.time.DayOfWeek;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class CalendarCalc {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // 日付の入力
        System.out.println("日付を入力してください (例: 2020-04-01): ");
        String input = scanner.nextLine();
        LocalDate inputDate = LocalDate.parse(input);
        
        // 当月カレンダーの作成
        LocalDate firstDayOfMonth = inputDate.with(TemporalAdjusters.firstDayOfMonth());
        LocalDate lastDayOfMonth = inputDate.with(TemporalAdjusters.lastDayOfMonth());
        
        LocalDate startCalendar = firstDayOfMonth.with(TemporalAdjusters.previousOrSame(DayOfWeek.SATURDAY));
        LocalDate endCalendar = lastDayOfMonth.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
        
        // カレンダーを作るために必要な週数
        long weeksInMonth = ChronoUnit.WEEKS.between(startCalendar, endCalendar);
        
        // 年末までの週数（入力された日付の週を含まない）
        LocalDate startNextWeek = inputDate.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
        LocalDate endOfYear = LocalDate.of(inputDate.getYear(), 12, 31);
        long weeksUntilEndOfYear =  ChronoUnit.WEEKS.between(startNextWeek, endOfYear)+1;
        
        // 結果の表示
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        System.out.println("カレンダー作成日: " + inputDate.format(formatter));
        System.out.println(firstDayOfMonth + "の週数: " + weeksInMonth + "週");
        System.out.println("年末までの週数: " + weeksUntilEndOfYear + "週");
        
        scanner.close();
    }
}