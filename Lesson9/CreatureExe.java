package Lesson9;

// Lesson9 挑戦問題
// 9-C-1 ~ 9-C-2

class Creature {
    String name;
    int hp;
    
    // コンストラクタの設定
    public Creature() {
        name = "スライム";
        hp = 10;
        // System.out.println("生物の名前は" + name + "です。");
        // System.out.println("生物の生命値は" + hp + "です。");
    }
    public void setName(String n) {
        name = n;
        System.out.println("生物の名前は" + name + "です。");
    }
    public void setHp(int h) {
        hp = h;

        if (h <= 0) {
            System.out.println("HPが0です。");
        } else {
            System.out.println("生物の生命値は" + hp + "です。");
        }           
    }
    public void setNameHp(String n, int h) {
        name = n;
        hp = h;
        // System.out.println("生物の名前は" + name + "です。");
        // System.out.println("生物の生命値は" + hp + "です。");
        // 上記を入力するとドラキー情報が2回出力されてしまう
    }
    public void show() {
        System.out.println("生物の名前は" + name + "です。");
        System.out.println("生物の生命値は" + hp + "です。");
    } 
}

public class CreatureExe {
    public static void main(String[] args) {
        Creature cre1 = new Creature();
        cre1.show();

        Creature cre2 = new Creature();
        cre2.setNameHp("ドラキー", 50);
        cre2.show();
    }
}
