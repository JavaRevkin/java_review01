package Lesson8;

// Lesson8 入門問題
// 8-A-1


// 車のクラス
class Car {
    int num;
    double gas;
}

// 車のクラスのオブジェクトを作成
public class Lesson8Sample1 {
    public static void main(String[] args) {
        Car car1;
        car1 = new Car();

        car1.num = 1234;
        car1.gas= 20.5;

        System.out.println("車のナンバーは" + car1.num + "です。");
        System.out.println("ガソリン量は" + car1.gas + "です。");
    }
}
