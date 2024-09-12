package a06;
import java.util.Scanner;
import java.text.NumberFormat;
import java.time.LocalDate;

public class TravelCost {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // 入力を受け取る
        System.out.println("距離単価 (1～100円)を入力してください");
        int intRangePrice= scanner.nextInt();

        System.out.println("往復距離 (0.01～100.00km)を入力してください");
        double dbRoundTrip = scanner.nextDouble();

        // 1日あたりの交通費を計算
        int intDayExpense = (int) Math.floor(intRangePrice * dbRoundTrip);

        // 現在の日付を取得
        LocalDate today = LocalDate.now();

        // 当月の営業日数を計算
        int intWorkingDays = calculateWorkingDays(today);

        // 1ヶ月の交通費を計算
        int intMonExpense = intDayExpense * intWorkingDays;

        // 非課税限度額を設定 (例として10kmの非課税限度額を使用)
        int intTaxFree = 7100;

        // 課税額と非課税額を計算
        int intTax = Math.max(0, intMonExpense - intTaxFree);
        int intExemption = Math.min(intMonExpense, intTaxFree);

        //カンマ区切りのインスタンス
        NumberFormat ni = NumberFormat.getNumberInstance();

        scanner.close();
        
        // 結果を表示
        System.out.println("距離単価:" + ni.format(intRangePrice) + "円");
        System.out.println("往復距離:" + dbRoundTrip + "km");
        System.out.println("営業日" + intWorkingDays + "日");
        System.out.println("1日あたりの交通費: " + ni.format(intDayExpense) + "円");
        System.out.println("1ヶ月の交通費: " + ni.format(intMonExpense) + "円");
        System.out.println("課税額: " + ni.format(intTax) + "円");
        System.out.println("非課税額: " + ni.format(intExemption) + "円");

    }
    
    // 当月の営業日数を計算するメソッド
    private static int calculateWorkingDays(LocalDate date) {
        LocalDate firstDay = date.withDayOfMonth(1);
        LocalDate lastDay = date.withDayOfMonth(date.lengthOfMonth());
        int workingDays = 0;

        for (LocalDate day = firstDay; !day.isAfter(lastDay); day = day.plusDays(1)) {
            if (day.getDayOfWeek().getValue() >= 1 && day.getDayOfWeek().getValue() <= 5) {
                workingDays++;
            }
        }

        return workingDays;
    }
}
