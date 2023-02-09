package Lesson4;

// Lesson4 
// 4-B-2

public class CalcAve {
    public static void main(String[] args){
        int a = 45;
        int b = 68;
        int c = 91;
        int d = 102;
        int e = 3;
        int f = 5;
        System.out.println("6個の平均は" + ((a + b + c + d + e + f) / 6) + "です。");

        System.out.println("6個の平均は" + (45 + 68 + 91 + 102 + 3 + 5) / 6 + "です。");

        int[] num = {45, 68, 91, 102, 3, 5};
        int totalSum = 0;
        for (int i = 0; i < num.length; i ++) {
            totalSum = totalSum + num[i];
        }        
        System.out.println("6個の平均は" + totalSum / num.length + "です。");
    }    
}
