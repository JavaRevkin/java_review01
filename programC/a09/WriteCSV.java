package a09;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

public class WriteCSV {
    public static void main(String[] args) {
        String inputFilePath = "C:\\Users\\work\\Desktop\\課題\\csv\\商品一覧.csv";
        String outputFilePath = "C:\\Users\\work\\Desktop\\課題\\csv\\売上.csv";
        
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(inputFilePath), "SJIS"));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFilePath), "SJIS"))) {

            String line;
            Scanner scanner = new Scanner(System.in);
            NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.JAPAN);

            // ヘッダーの読み込みと書き込み
            if ((line = br.readLine()) != null) {
                bw.write(line + ",販売数,売上金額");
                bw.newLine();
            }

            // データの読み込みと処理
            while ((line = br.readLine()) != null) {
                String[] columns = line.split(",");
                
                // 列数チェック
                if (columns.length < 3) {
                    System.out.println("列数が不足しているため、この行をスキップします: " + line);
                    continue;
                }

                String productCode = columns[0];
                String productName = columns[1];
                String unitPrice = columns[2];
                int intUnitPrice;

                try {
                    intUnitPrice = Integer.parseInt(unitPrice);
                } catch (NumberFormatException e) {
                    intUnitPrice = 0; // 数値でない場合は0として扱う
                }

                int salesQuantity = 0;
                while (true) {
                    System.out.print("商品コード：" + productCode + "\n商品名：" + productName + "\n単価：" + numberFormat.format(unitPrice) + "\n販売数：\n[画面入力] ");
                    salesQuantity = scanner.nextInt();
                    if (salesQuantity >= 1 && salesQuantity <= 100) {
                        break;
                    } else {
                        System.out.println("販売数は1から100の範囲で入力してください。");
                    }
                }

                int intSalesAmount = intUnitPrice * salesQuantity;
                String formattedSalesAmount = numberFormat.format(intSalesAmount);

                bw.write(productCode + "," + productName + "," + numberFormat.format(unitPrice) + "," + salesQuantity + "," + formattedSalesAmount);
                bw.newLine();
            }

            System.out.println("売上.csv を出力しました");

            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}