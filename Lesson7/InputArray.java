package Lesson7;

// Lesson7 練習問題
// 7-B-2

public class InputArray {
    public static void main(String[] args) {
        // int[] num  = {123, 436, 98, 254, 326};
        // int max = num[0];
        // int min = num[0];

        int max = Integer.parseInt(args[0]);
        int min = max ;

        for (int i = 1; i < args.length; i ++) {
            int m = Integer.parseInt(args[i]);
            if (m > max) {
                max = m;                
            }
            else if (m < min) {
                min = m;                
            }        
        }
    System.out.println("最大値:" + max);
    System.out.println("最小値:" + min);
    }
}
