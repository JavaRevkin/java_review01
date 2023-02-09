package Lesson5;

// Lesson5 挑戦問題
// 5-C-2

public class Omikuji {
    public static void main (String[] args) {
        int num = (int) (Math.random() * 6);

        switch (num) {
            case 0:
                System.out.println("あなたの運勢は大吉です");
                break;

            case 1:
                System.out.println("あなたの運勢は中吉です");
                break;

            case 2:
                System.out.println("あなたの運勢は小吉です");
                break;

            case 3:
                System.out.println("あなたの運勢は吉です");
                break;

            case 4:
                System.out.println("あなたの運勢は凶です");
                break;

            default:
                System.out.println("あなたの運勢は大凶です");
                break;     
        }             
    }
} 