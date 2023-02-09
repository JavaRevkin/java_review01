package Lesson6;

// Lesson6 練習問題
// 6-B-4

public class PowNumber {
    public static void main(String[] args) {
        int num = 2;
        int mul = 1;
        
        for (int i = 1; i <= 30; i ++) {
            mul = mul * num;
            System.out.println(num + "の" + i + "乗は" + mul + "です。");
        }
    }
}
