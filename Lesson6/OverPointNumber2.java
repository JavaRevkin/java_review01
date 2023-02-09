package Lesson6;

// Lesson6 練習問題
// 6-B-7

public class OverPointNumber2 {
    public static void main(String[] args) {
        int sum = 0;

        for (int i = 1; ; i ++) {
            sum += i;
            
            if (sum >= 10000) {
                System.out.println("総和は" + sum + "、最後に足した数は" + i + "です。");
                break;
            }
        }          
    }
}
