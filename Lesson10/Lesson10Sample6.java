package Lesson10;

// Lesson10 入門問題
// 10-A-7

class CarSample6 {
    private int num;
    private double gas;

    public CarSample6() {
        num = 0;
        gas = 0.0;
        System.out.println("車を作成しました。");
    }
    public void setCar(int n, double g) {
        num = n;
        gas = g;
        System.out.println("ナンバーを" + num + "にガソリン量を" + gas + "にしました。");
    }
    public void show() {
        System.out.println("車のナンバーは" + num + "です。");
        System.out.println("ガソリン量は" + gas + "です。");
    }
}

public class Lesson10Sample6 {
    public static void main (String[] args) {
        CarSample6 car1;
        System.out.println("car1を宣言しました。");
        car1 = new CarSample6();
        car1.setCar(1234, 20.5);
        
        CarSample6 car2;
        System.out.println("car2を宣言しました。");

        car2 = car1;
        System.out.println("car2にcar1を代入しました。");

        System.out.print("car1がさす");
        car1.show();
        System.out.print("car2がさす");
        car2.show();
    }
}
