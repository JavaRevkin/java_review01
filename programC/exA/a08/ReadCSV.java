package a08;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ReadCSV {
    public static void main(String[] args) {
        String csvFile = "C:\\Users\\work\\Desktop\\課題\\csv\\商品一覧.csv";
        String line;
        String csvSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(csvFile), "SJIS"))){
            List<String[]> data = new ArrayList<>();
            String[] headers = br.readLine().split(csvSplitBy); // ヘッダー行を読み込む

            while ((line = br.readLine()) != null) {
                String[] row = line.split(csvSplitBy, -1); // -1を指定して空の項目も保持
                for (int i = 0; i < row.length; i++) {
                    if (row[i].isEmpty()) {
                        row[i] = "未設定"; // 空の項目を「未設定」に置き換える
                    }
                }
                data.add(row);
            }
            
            // データを表示する
            for (String header : headers) {
                System.out.print(header + "\t" );
            }
            
            System.out.println();

            for (String[] row : data) {
                for (String item : row) {
                    System.out.printf(item+ "\t");
                }
                System.out.println();
            }
        
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}