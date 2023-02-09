package Lesson5;

// Lesson5 練習問題
// 5-B-4

import java.util.Scanner;

public class Bmi {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        
        System.out.println("身長をメートルで入力してください。");
        double height = scan.nextDouble();
        // nextDoubleは0.0から1.0の浮動小数の値を取得するためのメソッド

        System.out.println("体重をキログラムで入力してください。");
        double weight = scan.nextDouble();
        
        scan.close();

        double bmi = weight / (height * height);

        if (bmi <= 18.5) {
            System.out.println("あなたの肥満指数は" + bmi + "です。\nやせてます");
            return;
        } 

        if (bmi > 18.5 && bmi < 25) {
            System.out.println("あなたの肥満指数は" + bmi + "です。\n正常です");
            return;
        } 

        if (bmi >= 25 && bmi < 30) {
            System.out.println("あなたの肥満指数は" + bmi + "です。\n太ってます");
            return;
        } 

        else {
            System.out.println("あなたの肥満指数は" + bmi + "です。\n肥満です");       
        }
        
    }
}
