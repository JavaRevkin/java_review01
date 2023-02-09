package Lesson5;

// Lesson5 挑戦問題
// 5-C-1

import java.util.Scanner;

public class EtoYear {
    public static void main(String[] args) {
        System.out.println("調べたい歳を西暦で入力してください。");
        
        Scanner scan = new Scanner(System.in);
        
        int year = scan.nextInt();
        String[] etoMod = {"子", "丑", "寅", "卯", "辰", "巳", "午", "未", "申", "酉", "戌", "亥"};
        
        System.out.println(year + "は" + etoMod[(year + 8) % 12] + "年です。");
        // 西暦0年が申になる為 + 8 になる

        scan.close();
    }   
} 
