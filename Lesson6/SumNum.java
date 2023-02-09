package Lesson6;

// Lesson6 　練習問題
// 6-B-1

public class SumNum {
    public static void main(String[] args) {
        int sum = 0;
        for (int i = 1; i <= 100; i ++) {
            sum += i;
        }

        System.out.println("1から100までの合計は" + sum + "です。");
    }
}
