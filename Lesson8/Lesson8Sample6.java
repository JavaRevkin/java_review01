package Lesson8;

// Lesson8 入門問題
// 8-A-8 ~ 8-A-9

class CarSample6 {
    int num;
    double gas;
    String col;
    String name;

    int getNum() {
        System.out.println("ナンバーを調べました。");
        return num;
    }
    double getGas() {
        System.out.println("ガソリン量を調べました。") ;
        return gas;
    }
    String getCol() {
        System.out.println("色を調べました");
        return col;
    }
    String getName() {
        System.out.println("車種を調べました。");
        return name;
    }
    void setNumGas(int n, double g) {
        num = n;
        gas = g;
        System.out.println("車のナンバーを" + num + "にガソリンの量" + gas + "にしました。");
    }
    void setColName(String c, String a) {
        col = c;
        name = a;
        System.out.println("展示車の色は" + col + "とし、車種は" + name + "にしました。");
    }
    void show() {
        System.out.println("車のナンバーは" + num + "です。");
        System.out.println("ガソリンの量は" + gas + "です。");
        System.out.println("車の色は" + col + "です。");
        System.out.println("車種は" + name + "です。");
    }
}
public class Lesson8Sample6 {
    public static void main(String[] args) {
        CarSample6 car1 = new CarSample6();

        car1.setNumGas(1234, 20.5);
        car1.setColName("赤", "プリウス");

        int number = car1.getNum();
        double gasoline = car1.getGas();

        String color = car1.getCol();
        String carName = car1.getName();

        System.out.println("サンプル車を調べたところ");
        System.out.println("ナンバーは" + number + "ガソリン量は" + gasoline + "でした。");
        System.out.println("また、色は" + color + "で車種は" + carName + "でした。");
    }
}
