package Lesson6;

// Lesson6 
// 6-A-7 ~ 6-A-8

public class Lesson6Sample8 {
    public static void main(String[] args) {
        boolean bl = false;
        for (int i = 0; i < 5; i ++) {
            for (int j = 0; j < 9; j ++) {
                if (bl == false) {
                    System.out.print("=");
                    bl = true;
                }
                else {
                    System.out.print("+");
                    bl = false;
                }
            }
            System.out.println("\n");
        }
    }
}
