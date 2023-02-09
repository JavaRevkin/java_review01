package Lesson8;

// Lesson8 入門問題
// 8-A-4

class CarSample3 {
    int num;
    double gas;

    void show() {
        System.out.println("車のナンバーは"  + num + "です。");
        System.out.println("ガソリン量は" + gas + "です。");
    }
    void showCar() {
        System.out.println("これから車の情報を表示します。");
        show();
    }
}

public class Lesson8Sample3 {
    public static void main(String[] args) {
        CarSample3 car1;
        car1 = new CarSample3();

        car1.num = 1234;
        car1.gas = 20.5;

        car1.showCar();
    }    
}
