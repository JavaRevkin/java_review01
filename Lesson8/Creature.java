package Lesson8;

// Lesson8 挑戦問題
// 8-C-1

class Creature {
    String name;
    int hp;

    void setName(String n) {
        name = n;
        System.out.println("生物" + name + "が現れた!");
    }
    void setHp(int h) {
        hp = h;

        if (h <= 0) {
            System.out.println("生命値が0になりました。");
        } else {
            System.out.println("生命値は" + hp + "です。");
        }           

    }
    void show() {
        System.out.println("生物" + name + "の生命値は" + hp + "です。");
    }
}
// public class Creature {
//     public static void main(String[] args) {
//         CreatureA cre1 = new CreatureA();

//         String creatureName = "キノコ";
//         int hpoint = -5;

//         cre1.setName (creatureName);
//         cre1.setHp (hpoint);
//     }
// }