package Lesson5;

// Lesson5 練習問題
// 5-B-1

import java.io.*;

public class EvenOrOdd {
    public static void main(String[] args) throws IOException {
        System.out.println("整数を入力してください。");
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        int res = Integer.parseInt(str);

        if (res % 2 == 0) {
            System.out.println("偶数です");
            
        } else {
            System.out.println("奇数です");
        }
    }
}
