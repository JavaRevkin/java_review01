package Lesson10;

// Lesson10 入門問�?
// 10-A-3 ~ 10-A-4

import java.io.*;

public class Lesson10Sample3 {
    public static void main(String[] args) throws IOException {
        System.out.println("検索したいアルファベットを入力してください。");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str1 = "abcdefghijklmnopqrstuvwxyz";

        String str2 = br.readLine();
        String str = str2.toLowerCase();
        char ch = str.charAt(0);

        int num = str1.indexOf(ch);

        if (num != -1)
            System.out.println("A to Zの" + (num + 1) + "番目に「" + ch + "」が見つかりました。");
        
        else
            System.out.println(str1 + "に「" + ch + "」はありません。");
    }
}
