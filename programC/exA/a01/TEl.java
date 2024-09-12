package a01;

import java.util.Scanner;


public class TEl {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("電話番号を入力してください（例: 123-456-7890）:");
        String input = scanner.nextLine();

        scanner.close();

        String[] tels = input.split("-");
        if (tels.length != 3) {
            System.out.println("エラー: 電話番号の形式が正しくありません。例: 123-456-7890");
            return;
        }


        System.out.println("市外局番:" + tels[0]);
        System.out.println("市内局番:" + tels[1]);
        System.out.println("加入者番号:" + tels[2]);
    }
}