import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class RegisterProduct {
    public static final int MIN_PRICE = 1;

    public static final int MAX_PRICE = 999999;

    public static final int COLUMN_WIDTH = 16;

    public static void main(String[] args) {
        int pdNum = Integer.parseInt(args[0]);
        if (pdNum < 1 || pdNum >= 1000) {
            System.out.println("Number of products that can be entered is 1 to 999");
            return;
        }
        List<Product> products = new ArrayList<>();
        try (Scanner scan = new Scanner(System.in, "Shift-JIS")) {
            Set<String> pdSet = new HashSet<>();
            for (int pdCode = 0; pdCode < pdNum; pdCode++) {
                System.out.println("商品コード：" + pdCode);
                String pdName = "";
                do {
                    System.out.println("商品名（重複不可）：");
                    pdName = scan.nextLine();
                } while (pdSet.contains(pdName));
                pdSet.add(pdName);
                int pdPrice = 0;
                do {
                    System.out.println("単価（1～999,999の整数で入力）：");
                    String inputStr = scan.nextLine();
                    if (isInteger(inputStr)) pdPrice = Integer.parseInt(inputStr);
                    else continue;
                } while (pdPrice < MIN_PRICE || pdPrice > MAX_PRICE);
                products.add(new Product(pdCode, pdName, pdPrice));
                System.out.println();
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        System.out.println("商品一覧");
        Arrays.asList("商品コード", "商品名", "単価").forEach(str -> System.out.print(leftAlignment(str)));
        System.out.println();
        for (Product pd : products) { // 各商品毎
            pd.getInfo().forEach(str -> System.out.print(leftAlignment(str)));
            System.out.println();
        }
    }

    private static String leftAlignment(final String target) {
        int byteDiff = (target.getBytes(StandardCharsets.UTF_8).length - target.length()) / 2;
        return String.format("%-" + (COLUMN_WIDTH - byteDiff) + "s", target);
    }

    private static boolean isInteger(final String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch(NumberFormatException e) {
            return false;
        }
    }
}