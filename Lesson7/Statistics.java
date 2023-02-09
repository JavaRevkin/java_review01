package Lesson7;

// Lesson7 挑戦問題
// 7-C-1

public class Statistics {
    public static void main(String[] args) {
        int[] array = new int[100];

        for (int i = 0; i < array.length; i ++) {
            array[i] = (int)((Math.random() * 200) + 1);
            System.out.print(array[i] + " ");         
        }
        // 平均値を求める
        int sum = 0;
        int sum2 = 0; 
        for (int i = 0; i < array.length; i ++){
            sum += array[i];
            sum2 += array[i] * array[i];
        }
        double mean = sum / array.length;
        System.out.println("\n平均値は" + mean + "です。"); 

        // 分散を求める
        double var = sum2 / (double)array.length - mean * mean;

        // 標準偏差を求める
        double std = Math.sqrt(var);
        System.out.println("標準偏差は" + std + "です。");

        // 中央値を求める
        double median = 0;
        int m = array.length / 2;
        if ((array.length % 2) != 0) {
            // 配列の個数が奇数だったら
            median = (double)array[m];
        } else {
            // 配列の個数が偶数だったら
            median = (double)(array[m - 1] + array[m]) / 2.0;
        }
        System.out.println("中央値は" + median + "です。");

        // 最頻値を求める
        int num = 1;
        int maxNum = 1;
        int mode = array[0];
        int preMode = array[0];

        for (int i = 1; i < array.length; ++ i) {
            if (preMode == array[i]) {
                ++ num;
            } else {
                if (num > maxNum) {
                    mode = preMode;
                    maxNum = num;
                }
                preMode = array[i];
                num = 1;
            }
        }
        if (num > maxNum) {
            mode = preMode;
        }        
        System.out.println("最頻値は" + mode + "です。");
    }
}
