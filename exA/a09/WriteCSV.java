import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class WriteCSV {
    public static final int MIN_SALES = 1;

    public static final int MAX_SALES = 100;

    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("../商品一覧.csv"), "Shift-JIS"));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("../売上.csv"),"Shift-JIS"));
            String line = br.readLine();
            bw.write(line + ",販売数,売上金額\n");
            List<String> header = Arrays.asList(line.split(",", -1));
            int unitPriceCol = header.indexOf("単価"); // 単価が格納されている列のindex
            try (Scanner scan = new Scanner(System.in)) {
                while ((line = br.readLine()) != null) { // 各商品について
                    bw.write(line);
                    List<String> cells = Arrays.asList(line.split(",", -1));
                    cells.replaceAll(cell -> cell != "" ? cell : "未設定");
                    if (isInteger(cells.get(unitPriceCol))) { // 単価が空でない時
                        for (int i = 0; i < header.size(); i++) System.out.println(header.get(i) + "：" + numForm(cells.get(i))); // 商品情報
                        int sales = 0;
                        do {
                            System.out.println("販売数（1～100の整数で入力）：");
                            String inputStr = scan.nextLine();
                            if (isInteger(inputStr)) sales = Integer.parseInt(inputStr);
                            else continue;
                        } while (sales < MIN_SALES || sales > MAX_SALES);
                        bw.write("," + sales + "," + (sales * Integer.parseInt(cells.get(unitPriceCol))) + "\n");
                        System.out.println();
                    } else bw.write(",,\n"); // 単価が空の時
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            br.close();
            bw.close();
            System.out.println("売上.csvを出力しました。");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String numForm(final String target) {
        return isInteger(target) ? new DecimalFormat(",000").format(Integer.parseInt(target)) : target;
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