package Lesson7;

// Lesson7 入門問題
// 7-A-5 ~ 7-A-6

public class Lesson7Sample8 {
    public static void main(String[] args) {
        int[] test = {77, 65, 52, 48, 71, 63, 59};

        for (int i = 0; i < test.length; i ++) {
            System.out.println((i + 1) + "番目の人の点数は" + test[i] + "です。");
        }
    System.out.println("テストの受験者は" + test.length + "人です。");
    }
}
