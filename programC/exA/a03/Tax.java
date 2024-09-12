package a03;
import java.text.NumberFormat;
import java.util.Scanner;


public class Tax {
    public static void main(String[] args) {
       
        Scanner scanner = new Scanner(System.in);

        // 金額の入力
        System.out.println("金額を入力してください (0～99999): ");
        int intPrice = scanner.nextInt();
        
        // 税率の入力
        System.out.println("税率を入力してください (例: 10): ");
        int intTax = scanner.nextInt();

        scanner.close();

        // 範囲チェック
        if (intPrice < 0 || intPrice > 99999) {
            System.out.println("金額は0から99,999の範囲で入力してください。");
            return;
        }
        // 計算
        
        //税込金額
        int intTotalPrice = intPrice + (intPrice/intTax);
        //税込の消費税額
        int intTaxPrice = intTotalPrice - intPrice;
        
        //税抜金額
        int intExcPrice = (int)(intPrice/(intTax/100.0+1));
        //税抜の消費税額
        int intTaxExc = intPrice-intExcPrice;
        
        //カンマ区切りのインスタンス
        
        NumberFormat ni = NumberFormat.getNumberInstance();

         // 結果を表示
         System.out.println("税率:" + intTax + "%");
         System.out.println("税込金額:" + ni.format(intTotalPrice) + "円" + "消費税額:" + ni.format(intTaxPrice) + "円");
         System.out.println("税抜金額:" + ni.format(intExcPrice) + "円" + "消費税額" + ni.format(intTaxExc) + "円");
    }
}
