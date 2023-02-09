package Lesson9;

// Lesson9 入門問題
// 9-A-4

class CarSample4 {
    private int num;
    private double gas;

    public CarSample4() {
        num = 0;
        gas = 0.0;
        System.out.println("車を作成しました。");
    }
    public void show() {
        System.out.println("車のナンバーは" + num + "です。");
        System.out.println("ガソリン量は" + gas + "です。");
    }
}
public class Lesson9Sample4 {
    public static void main(String[] args) {
        CarSample4 car1 = new CarSample4();

        car1.show();
    }
}
