package Lesson10;

// Lesson10 入門問題
// 10-A-1 ~ 10-A-2

public class Lesson10Sample1 {
    public static void main(String[] args) {
        String str = "あなたのルイボスティー";

        char ch1 = str.charAt(7);
        char ch2 = str.charAt(2);
        char ch3 = str.charAt(10);

        int len= str.length();

        System.out.println(str + "の8番目の文字は" + ch1 + "です。");
        System.out.println(str + "の5番目の文字は" + ch2 + "です。");
        System.out.println(str + "の4番目の文字は" + ch3 + "です。");
        System.out.println(str + "の長さは" + len + "です ☆");
    }
}
