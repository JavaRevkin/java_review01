package Lesson10;

// Lesson10 入門問題
// 10-A-8

class CarSample8 {
    private int num;
    private double gas;
    private String name;

    public CarSample8() {
        num = 0;
        gas = 0.0;
        name = "名無し";
        System.out.println("車を作成しました。");
    }
    public void setCar(int n, double g) {
        num = n;
        gas = g;
        System.out.println("ナンバーを" + num + "ガソリン量を" + gas + "にしました。");
    }
    public void setName(String nm) {
        name = nm;
        System.out.println("名前を" + name + "にしました。");
    }
    public void show() {
        System.out.println("車のナンバーは" + num + "です。");
        System.out.println("ガソリン量は" + gas + "です。");
        System.out.println("名前は" + name + "です。");
    }
}

public class Lesson10Sample8 {
    public static void main(String[] args) {
        CarSample8 car1;
        car1 = new CarSample8();

        car1.show();

        int number = 1234;
        double gasoline = 20.5;
        String str = "1号車";

        car1.setCar(number, gasoline);
        car1.setName(str);

        car1.show();
    }
}
