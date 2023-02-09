package Lesson4;

// Lesson4 挑戦問題
// 4-C-1

public class CalcTime {
    public static void main(String[] args) {
        int timeMin = 680;
        int hour = timeMin / 60;
        int min = timeMin % 60;
        System.out.println(timeMin + "分は" + hour + "時間" + min + "分です。");
    }
}
