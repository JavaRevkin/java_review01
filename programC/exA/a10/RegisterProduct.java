package a10;

import java.util.ArrayList;
import java.util.Scanner;

public class RegisterProduct {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in,"Shift-JIS");
        ArrayList<Product> products = new ArrayList<>();
        int intCode = 1;

        System.out.print("登録する商品の件数を入力してください: ");
        int intItemCount = scanner.nextInt();
        scanner.nextLine(); // 改行を消費

        for (int i = 0; i < intItemCount; i++) {
            System.out.print("商品コード: " + String.format("%03d", intCode) + "\n商品名を入力してください: ");
            String name = scanner.nextLine();

            // 同名の商品が既に登録されているか確認
            boolean exists = false;
            for (Product product : products) {
                if (product.getName().equals(name)) {
                    exists = true;
                    break;
                }
            }

            if (exists) {
                System.out.println("同名の商品が既に登録されています。");
                i--; // 同じインデックスで再入力
                continue;
            }

            System.out.print("単価を入力してください (1～999,999): ");
            int intPrice = scanner.nextInt();
            scanner.nextLine(); // 改行を消費

            if (intPrice < 1 || intPrice > 999999) {
                System.out.println("単価は1～999,999の範囲で入力してください。");
                i--; // 同じインデックスで再入力
                continue;
            }

            products.add(new Product(intCode++, name, intPrice));
        }

        System.out.println("\n登録された商品一覧:");
        System.out.println("商品コード 商品名 単価");
        for (Product product : products) {
            System.out.println(String.format("%03d", product.getCode()) + " " + product.getName() + " " + product.getPrice());
        }
        scanner.close();
    }
}
    

