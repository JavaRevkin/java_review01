package Lesson7;

// Lesson7 入門問題
// 7-A-1 ~ 7-B-2

public class Lesson7Sample3 {
    public static void main(String[] args) {
        int[] test = new int[7];

        test[0] = 79;
        test[1] = 72;
        test[2] = 42;
        test[3] = 51;
        test[4] = 65;
        test[5] = 74;
        test[6] = 56;

        for (int i = 0; i < 7; i ++) {
            System.out.println((i + 1) + "番目の人の点数は" + test[i] + "です。");
        }
    }
}
