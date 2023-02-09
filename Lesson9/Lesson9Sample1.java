package Lesson9;

// Lesson9 入門問題
// 9-A-1

class CarSample1 {
    int num;
    double gas;

    void show() {
        System.out.println("車のナンバーは" + num + "です。");
        System.out.println("ガソリン量は" + gas + "です。");
    }
}
public class Lesson9Sample1 {
    public static void main(String[] args) {
        CarSample1 car1 = new CarSample1();

        car1.num = 1234;
        car1.gas = 20.5;

        car1.show();
    }
}
