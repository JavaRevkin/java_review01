package Lesson8;

// Lesson8 入門問題
// 8-A-6 ~ 8-A-7

class CarSample5{
    int num;
    double gas;
    String col;
    String name;

    void setNumGas(int n, double g) {
        num = n;
        gas = g;
        System.out.println("車のナンバーを" + num + "にガソリン量を" + gas + "にしました。");
    }
    void setInfo(String c, String a) {
        col = c;
        name = a;
        System.out.println("車の色は" + col + "で車種は" + name + "です。");
    } 
    void show() {
        System.out.println("車のナンバーは" + num + "です。");
        System.out.println("ガソリン量は" + gas + "です。");
    }
}
public class Lesson8Sample5 {
    public static void main(String[] args) {
        CarSample5 car1 = new CarSample5();

        int number = 1234;
        double gasoline = 20.5;

        car1.setNumGas(number, gasoline);

        String color = "茶";
        String carName = "ポルテ";

        car1.setInfo(color, carName);
    }
}
