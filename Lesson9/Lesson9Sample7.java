package Lesson9;

// Lesson9 入門問題
// 9-A-5

class CarSample7 {
    private int num;
    private double gas;

    public CarSample7() {
        num = 0;
        gas = 0.0;
        System.out.println("車を作成しました。");
    }
    public void setCar(int n, double g) {
        num = n;
        gas = g;
        System.out.println("ナンバーを" + num + "ガソリン量を" + gas + "にしました。");
    }
    public void show() {
        System.out.println("車のナンバーは" + num + "です。");
        System.out.println("ガソリン量は" + gas + "です。");
    }
}
public class Lesson9Sample7 {
    public static void main(String[] args) {
        CarSample7 car1 = new CarSample7();
        car1.setCar(1234, 20.5);
        car1.show();

        CarSample7 car2 = new CarSample7();
        car2.setCar(4567, 30.5);
        car2.show();
    }
}
