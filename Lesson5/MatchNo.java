package Lesson5;

// Lesson5 挑戦問題
// 5-C-3

import java.util.Scanner;

public class MatchNo {
    public static void main(String[] args) {
        System.out.println("1～9の整数を3つ入力してください。");

        Scanner stdIn = new Scanner(System.in);
        int[] str = {9, 2, 5};
        // 配列の初期化

        int[] num = new int[3];
        // int型の3つ{9, 2, 5}の要素

        int point;
        // 追加点数

        int res = 0;
        // 合計点       

        for (int i = 0; i < 3; i ++) {
            num[i] = stdIn.nextInt();
        }

        for (int i = 0; i < 2; i ++) {
            for (int j = i + 1; j < 3; j ++) {
                if (num[i] == num[j])
                    num[j] = 0;
            }
        }
        
        for (int i = 0; i < 3; i ++) {
            for (int j = 0; j < 3; j ++) {
                if (str[i] == num[j]) {
                    if(i == j) {
                    // i と j の数字が同じで場所も同じだった場合
                        point = 2;                        
                        res = res + point;
                    } else {
                    // i　と j の数字は同じだが場所が違う場合
                        point = 1;                       
                        res = res + point;
                    }
                }
            }
        }        
        System.out.println("点数は" + res + "点です。");

        stdIn.close();
    }
}
