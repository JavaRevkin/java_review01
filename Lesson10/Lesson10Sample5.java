package Lesson10;

// Lesson10 入門問題
// 10-A-5 ~10-A-6

import java.io.*;
import java.util.Arrays;

public class Lesson10Sample5 {
    public static void main(String[] args) throws IOException {
        // System.out.println("整数を2つ入力してください。");

        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // String str1 = br.readLine();
        // String str2 = br.readLine();

        // int num1 = Integer.parseInt(str1);
        // int num2 = Integer.parseInt(str2);

        // int ans = Math.max(num1, num2);

        // System.out.println(num1 + "と" + num2 + "のうち大きいほうは" + ans + "です。");
        
        int[] array = {5, 6, 10, 3, 2, 7, 9};
        int max = 0;
        for (int i = 0; i < array.length; i ++) {
            max = Math.max(max, array[i]); 
        }
    System.out.println("リスト" + Arrays.toString(array) + "の中で1番大きな数字は" + max + "です。");      
    }
}
