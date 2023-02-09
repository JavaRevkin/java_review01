package Lesson6;

// Lesson6 入門問題
// 6-A-6

public class Lesson6Sample7 {
    public static void main(String[] args) {
        for (int i = 0; i < 5; i ++) {
        // 作業を5回繰り返す(外周ループ5回)
        
            for (int j = 0; j < 3; j ++) {
            // 0~2までそれぞれ3回繰り返す(外周ループ1回ごとに内周ループを3回処理)

                System.out.println("iは" + i + ":jは" + j);
            }
        }
    }
}
