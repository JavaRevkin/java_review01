package Lesson5;

// Lesson5 
// 5-A-6 ~ 5-A-7

import java.io.*;

public class Lesson5Sample5 {
    public static void main(String[] args) throws IOException {
        System.out.println("整数を入力してください。");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        int res = Integer.parseInt(str);

        switch (res) {
            case 5:
                System.out.println("5を入力しました。");
                break;

            case 10:
                System.out.println("10を入力しました。");
                break;
                
            default:
                System.out.println("5か10を入力してください。");
                break;
        }
    }
}
