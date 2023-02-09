package Lesson5;

// Lesson5 入門問題
// 5-A-8 ~ 5-A-9

import java.io.*;

public class Lesson5Sample7 {
    public static void main(String[] args) throws IOException {
        System.out.println("あなたは猫が好きですか？");
        System.out.println("YまたはNを入力してください。");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        char res = str.charAt(0);

        if (res == 'Y' || res == 'y') {
            System.out.println("猫って可愛いですよね。");

        } else if (res == 'N' || res == 'n') {
            System.out.println("そのうち猫の魅力に気づきます。");
            
        } else {
            System.out.println("YまたはNを入力してください。");
        }
    }
}
