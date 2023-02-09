package Lesson9;

// Lesson9 練習問題
// 9-B-1

class Dog {
    private int age;
    private String name;

    public Dog() {
        age = 0;
    }
    public void setInfo(String a, int b) {
        name = a;
        age = b;
        System.out.println("犬の名前を" + name + "に年齢を" + age + "にしました。");
    }
    public void show() {
        System.out.println("犬の名前は" + name + "です。\n犬の年齢は" + age + "歳です。");
    }
}

public class DogExec {
    public static void main(String[] args) {
        Dog dog1 = new Dog();
        dog1.setInfo("ポチ", 8);

        dog1.show();

        Dog dog2 = new Dog();
        dog2.setInfo("タロウ", 2);

        dog2.show();
    }
}
