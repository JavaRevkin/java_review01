import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;

public class ReadCSV {
    public static final int COLUMN_WIDTH = 16;

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("../商品一覧.csv"), "Shift-JIS"))) {
            String line;
            while ((line = br.readLine()) != null) {
                List<String> cells = Arrays.asList(line.split(",", -1));
                cells.replaceAll(cell -> cell != "" ? cell : "未設定");
                for (String str : cells) System.out.print(leftAlignment(str));
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String leftAlignment(String target) {
        try { // 文字列が数値（整数）の時はフォーマットを適用
            target = new DecimalFormat(",000").format(Integer.parseInt(target));
        } catch(NumberFormatException e) {
        }
        int byteDiff = (target.getBytes(StandardCharsets.UTF_8).length - target.length()) / 2;
        return String.format("%-" + (COLUMN_WIDTH - byteDiff) + "s", target);
    }
}