package exA.a02;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *  年月度の表示
 *   締日を20日とし、現在日付が属する年月度を現在の日付と合わせて表示する
 * 
 * @author 才間
 */

public class ClosingDate{
    
    //締日の設定
    static final int CLOSING_DAY = 20;
    
    public static void main(String[] args){
        
        //現在時間の取得
        LocalDateTime now = LocalDateTime.now();

        //現在時間のフォーマット設定
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日");
        //締日のフォーマット設定
        DateTimeFormatter ClosingFormatter = DateTimeFormatter.ofPattern("yyyy年MM月");
        
        //現在の年月日
        String nowFormat = now.format(formatter);
        //締日の年月
        String closingFormat = null;
        //現在の月日取得
        int day = now.getDayOfMonth();

        //現在の日付が締日の前後判定
        if (day < CLOSING_DAY){
            //締日より前ならそのまま
            closingFormat = now.format(ClosingFormatter);
        } else {
            //締日より後なら次月に移行
            now = now.plusMonths(1); //ここの数字を変えれば検証できる
            closingFormat = now.format(ClosingFormatter);

        }
        //出力
        System.out.println("現在の日付：" + nowFormat + "\n" + "年月度：" + closingFormat);
        
    }

}