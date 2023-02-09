package Lesson6;

// Lesson6 練習問題
// 6-B-3

import java.io.*;

public class Hellofor {
    public static void main(String[] args) throws IOException {
        System.out.println("整数を入力してください。");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();
        int num = Integer.parseInt(str);

        if (num <= 20) {
            for (int i = 1; i <= num; i ++) {
                System.out.println("Hello!");            
            }
            
        } else {
            System.out.println("数字は1～20の間で入力してください。");
        }
        
    }
}
