package Lesson9;

// Lesson9 入門問題
// 9-A-6 ~ 9-A-7

class CarSample8 {
    public static int sum = 0;

    private int num;
    private double gas;
    private String col;
    private String name;

    public CarSample8() {
        num = 0;
        gas = 0.0;
        sum ++;
        System.out.println("車を作成しました。");
    }
    public void setCar(int n, double g) {
        num = n;
        gas = g;
        System.out.println("ナンバーを" + num + "にガソリン量を" + gas + "にしました。");
    }
    public void setCarInfo(String c, String a) {
        col = c;
        name = a;
        System.out.println("車の色は" + col + "で車種を" + name + "にしました。");
    }
    public static void showSum() {
        System.out.println("車は全部で" + sum + "台あります。");
    }
    public void show() {
        System.out.println("車のナンバーは" + num + "です。");
        System.out.println("ガソリン量は" + gas + "です。");
        System.out.println("車の色は" + col + "です。");
        System.out.println("車種は" + name + "です。") ;      
    }
}
public class Lesson9Sample8 {
    public static void main(String[] args) {
        CarSample8.showSum();

        CarSample8 car1 = new CarSample8();
        car1.setCar(1234, 20.5);
        car1.setCarInfo("青", "シエンタ");

        CarSample8.showSum();

        CarSample8 car2 = new CarSample8();
        car2.setCar(4567, 30.5);
        car2.setCarInfo("白", "ハスラー");

        CarSample8.showSum();
    }
}
