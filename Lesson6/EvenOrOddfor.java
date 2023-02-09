package Lesson6;

// Lesson6 練習問題
// 6-B-2

public class EvenOrOddfor {
    public static void main(String[] args) {
        for (int i = 1; i <= 10; i ++) {
        // i = 1から初めて10以下の数字を表示
        
            if (i % 2 == 0) {
                System.out.println(i + "は偶数です。");

            } else {
                System.out.println(i + "は奇数です。");
            }
        }
    }
}
