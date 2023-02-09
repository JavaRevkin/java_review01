package Lesson7;

// Lesson7 練習問題
// 7-B-3

public class ArraySort {
    public static void main(String[] args) {
        int[] array = new int[10];
        // 要素数10の配列を作成

        System.out.println("ソート前");

        for (int i = 0; i < array.length; i ++) {
            array[i] = (int)((Math.random() * 20) + 1);
            System.out.print(array[i] + " ");         
        }
        
        System.out.println("\n昇順ソート後");
        
        for (int j = 0; j < array.length; j ++) {
            for (int t = j + 1; t < array.length; t ++) {
                if (array[t] < array[j]) {
                    int num = array[t];
                    array[t] = array[j];
                    array[j] = num;
                }
            }
        }
        for (int s = 0; s < array.length; s ++) {  
            System.out.print(array[s] + " ");                 
        }
    }
}
