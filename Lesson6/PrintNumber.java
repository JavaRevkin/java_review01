package Lesson6;

// Lesson6 挑戦問題
// 6-C-4

public class PrintNumber {
    public static void main(String[] args) {
        int count = 0;
        for (int i = 2; i <= 1000; i ++) {
            for (int j = 2; (j < i && i % j != 0) || j == i; j ++) {
                if (j == i){
                    count ++;
                    System.out.print(i + " ");
                }               
            }
        }
    System.out.println("\n素数の数は" + count + "個です。");
    }
}
