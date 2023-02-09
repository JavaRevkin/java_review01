package Lesson5;

// Lesson5 練習問題
// 5-B-2

import java.io.*;

public class LeapYear {
    public static void main(String[] args) throws IOException {
        System.out.println("西暦を入力してください。");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        int res = Integer.parseInt(str);  
              
        if ((res % 4 == 0 && res % 100 != 0) || (res % 400 == 0)) {
            // 4で割り切れて100で割り切れないのは、うるう年
            // 400で割り切れるのは、うるう年
            // 4で割り切れるが100で割り切れるのは、うるう年ではない

            System.out.println(res + "年はうるう年です。");

        } else {
            System.out.println(res + "年はうるう年ではありません。");
        } 
    }
}
