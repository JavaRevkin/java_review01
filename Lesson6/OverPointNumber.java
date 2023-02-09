package Lesson6;

// Lesson6 練習問題
// 6-B-6

public class OverPointNumber {
    /**
     * @param args
     */
    public static void main(String[] args) {
        int num = 2;
        int mul = 1;

        for (int i = 1; ; i ++) {
            mul = mul * num;
            if (mul >= 100000) {
                System.out.println(num + "のn乗 > 100000を満たす最小のnは" + i + "です。");
                System.out.println("その時の値は" + mul + "です。");
                break;
            }         
        }
    }
}
