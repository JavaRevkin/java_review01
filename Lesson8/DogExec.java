package Lesson8;

// Lesson8 練習問題
// 8-B-1

class Dog {
    String name;
    int age;

    void setDog(String n, int a) {
        name = n;
        age = a;
        System.out.println("犬の名前を" + name + "に年齢を" + age + "にしました。");
    }
    void show() {
        System.out.println("犬の名前は" + name + "です。\n犬の年齢は" + age + "歳です。");
    }
}

public class DogExec {
    public static void main(String[] args) {
        Dog dog1 = new Dog();
        String dogName1 = "ポチ";
        int dogAge1 = 8;
        dog1.setDog(dogName1, dogAge1);
        
        Dog dog2 = new Dog();
        String dogName2 = "シロ";
        int dogAge2 = 3;
        dog2.setDog(dogName2, dogAge2);

        dog1.show();
        dog2.show();
    }
}

// class Dog {
//     int age1;
//     int age2;
//     String name1;
//     String name2;

//     void setPochi(String n, int a) {
//         name1 = n;
//         age1 = a;
//         System.out.println("犬の名前を" + name1 + "に年齢を" + age1 + "にしました。");
//     }
//     void setShiro(String nn, int aa) {
//         name2 = nn;
//         age2 = aa;
//         System.out.println("犬の名前は" + name2 + "に年齢を" + age2 + "にしました。");
//     }
//     void show() {
//         System.out.println("犬の名前は" + name1 + "です。\n犬の年齢は" + age1 + "歳です。");
//         System.out.println("犬の名前は" + name2 + "です。\n犬の年齢は" + age2 + "歳です。");
//     }
// }
// public class DogExec {
//     public static void main(String[] args) {
//         Dog dog1 = new Dog();

//         String dogName1 = "ポチ";
//         int dogAge1 = 8;

//         dog1.setPochi(dogName1, dogAge1);

//         String dogName2 = "シロ";
//         int dogAge2 = 3;

//         dog1.setShiro(dogName2, dogAge2);

//         dog1.show();
//     }
// }


