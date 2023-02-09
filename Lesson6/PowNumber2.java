package Lesson6;

// Lesson6 挑戦問題
// 6-C-2

public class PowNumber2 {
    public static void main(String[] args) {
        long num = 1;
        
        for (int i = 0; i < 6; i ++) { 
            System.out.println(num + "*" + num + "=" + num * num);
            num = num * 10 + 1;  

        }
    }
}
