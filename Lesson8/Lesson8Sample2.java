package Lesson8;

// Lesson8 入門問題
// 8-A-2 ~ 8-A-3

class CarSample2 {
    int num;
    double gas;
    String col;
    String model;

    void show() {
    // 車の情報を表示する機能
    
        System.out.println("車のナンバーは" + num + "です。");
        System.out.println("ガソリン量は" + gas + "です。");
        System.out.println("車の色は" + col + "です。");
        System.out.println("車種は" + model + "です。");
    }
}

public class Lesson8Sample2 {
    public static void main(String[] args) {
        CarSample2 car1;
        car1 = new CarSample2();

        car1.num = 1234;
        car1.gas = 20.5;
        car1.col = "黒";
        car1.model = "VOXY";

        car1.show();
        car1.show();
    }
}
