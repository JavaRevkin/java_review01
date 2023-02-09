package Lesson5;

// Lesson5 入門問題
// 5-A-1 ~ 5-A-2

import java.io.*;

public class Lesson5Sample2 {
    public static void main(String[]args) throws IOException {
        System.out.println("整数を入力してください。");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReaderはテキストファイルを読み込むためのクラス
        // InputStreamReaderはバイト・ストリームから文字ストリームへの橋渡しの役目を持つ
        // ストリームとはデータを受け渡しするための手続きを抽象化したもの

        String str = br.readLine();
        // readlineはテキストファイルを1行ずつ読み込むメソッド

        int res = Integer.parseInt(str);
        // Integer.parseIntは引数に与えられたString型の数値をint型に変換して返すメソッド

        if (res < 5) {
            System.out.println("5未満が入力されました。");
            System.out.println("5未満が選択されました。");
        }
        
        System.out.println("処理を終了します。");
    }
    
}
