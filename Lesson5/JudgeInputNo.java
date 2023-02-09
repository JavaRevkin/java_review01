package Lesson5;

// Lesson5 練習問題
// 5-B-3

import java.io.*;

public class JudgeInputNo {
    public static void main(String[] args) throws IOException {
        System.out.println("整数を入力してください。");
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        int res = Integer.parseInt(str);

        if (res >= (35 + 10)) {
            System.out.println("大きい");
            return;
        } 

        if (res <= (35 - 10)) {
            System.out.println("小さい");
            return;
        } 

        if (res >= (35 + 1) && res <= (35 + 9)) {
            System.out.println("少し大きい");
            return;
        } 

        if (res <= (35-1) && res >= (35 - 9)) {
            System.out.println("少し小さい");
            return;
        }

        else {
            System.out.println("正解");       
        }
    }
}
