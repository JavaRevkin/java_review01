package Lesson4;

// 入門問題
// 4-A-5 ~ 4-A-6

public class Lesson4Sample5 {
    public static void main(String[] args) {
        int a = 7;
        int b = 3;
        b = a ++;
        System.out.println("代入後にインクリメントしたのでbの値は" + b + "です。");

        int c = 5;
        int d = 9;
        d = ++ c;
        System.out.println("代入前にインクリメントしたのでdの値は" + d + "です。");

        int aa = 7;
        int bb = 3;
        bb = aa --;
        System.out.println("代入後にデクリメントしたのでbbの値は" + bb + "です。");

        int cc = 5;
        int dd = 9;
        dd = -- cc;
        System.out.println("代入前にデクリメントしたのでddの値は" + dd + "です。");
    }
}
