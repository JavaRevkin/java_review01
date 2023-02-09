package Lesson8;

// Lesson8 入門問題
// 8-A-5

class CarSample4 {
    int num;
    double gas;

    void setNum(int n) {
        num = n;
        System.out.println("ナンバーを" + num + "にしました。");
    }
    void setGas(double g) {
        gas = g;
        System.out.println("ガソリン量を" + gas + "にしました。");
    }
    void show() {
        System.out.println("車のナンバーは" + num + "です。");
        System.out.println("ガソリン量は" + gas + "です。");
    }
}
public class Lesson8Sample4 {
    public static void main(String[] args) {
        CarSample4 car1 = new CarSample4();
    
        car1.setNum(1234);
        car1.setGas(20.5);
    }
}
