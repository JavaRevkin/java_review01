package Lesson6;

// Lesson6 挑戦問題
// 6-C-5

import java.io.*;

public class LeastCommonMultiple {

    public static void main(String[] args) throws IOException {
        System.out.println("2つの整数を入力してください。");

        BufferedReader br = new BufferedReader (new InputStreamReader(System.in));

        String str1 = br.readLine();
        String str2 = br.readLine();

        int a = Integer.parseInt(str1);
        int b = Integer.parseInt(str2);

        int num1 = a;
        int num2 = b;
        int num3;
        int lcm;
        int gcd;

        if (num1 <= 0 | num2 <= 0) {
            System.out.println("整数を入力してください。");
            
        } else {
            while ((num3 = num1 % num2) != 0) {
                num1 = num2;
                num2 = num3;
            }
            gcd = num2;
            lcm = a * b / gcd;
            System.out.println(str1 + "と" + str2 + "の最小公倍数は" + lcm + "です。");
        }
    }
}
