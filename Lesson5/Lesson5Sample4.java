package Lesson5;

// Lesson5 
// 5-A-4 ~ 5-A-5

import java.io.*;

public class Lesson5Sample4 {
    public static void main(String[] args) throws IOException {
        System.out.println("整数を入力してください。");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        int res = Integer.parseInt(str);

        if (res <= 5) {
            System.out.println("5以下が入力されました。");

        } else if (res > 5 & res <= 10) {
            System.out.println("5より大きい数字が入力されました。");
            
        } else {
            System.out.println("10以下の数字を入力してください。");
        }
    }
}
