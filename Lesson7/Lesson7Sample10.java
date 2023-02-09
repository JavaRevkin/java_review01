package Lesson7;

// Lesson7 入門問題
// 7-A-7 ~ 7-A-8

public class Lesson7Sample10 {
    public static void main(String[] args) {
        int[][] test;
        test = new int[3][5];

        test[0][0] = 72;
        test[0][1] = 81;
        test[0][2] = 56;
        test[0][3] = 62;
        test[0][4] = 84;
        test[1][0] = 68;
        test[1][1] = 67;
        test[1][2] = 76;
        test[1][3] = 71;
        test[1][4] = 82;
        test[2][0] = 69;
        test[2][1] = 74;
        test[2][2] = 59;
        test[2][3] = 87;
        test[2][4] = 77;

        for (int i = 0; i < 5; i ++) {
            System.out.println((i + 1) + "番目の人の国語の点数は" + test[0][i] + "です。");
            System.out.println((i + 1) + "番目の人の算数の点数は" + test[1][i] + "です。");
            System.out.println((i + 1) + "番目の人の英語の点数は" + test[2][i] + "です。");
        }
    }
}
