package exA.a09;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *   [商品一覧.csv]を読み込み、画面で入力した販売数をもとに「販売数」と「売上金額」の2項目追加した[売上.csv]を作成する
 *     出力するCSVの金額項目は3桁カンマ区切り下た状態で出力する
 *     また、単価が空となっている商品以外すべての商品の販売数を画面に順に入力していくものとし、未入力は許可しない。入力できる販売数は1～100の範囲
 * 
 * @author 才間
 */

public class WriteCSV{

    //csvのデータを管理するリストの準備
    private static ArrayList<String[]> csvDate = new ArrayList<String[]>();

    public static void main(String[] args){
        
        //[try-with-resources]使用
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
        new FileInputStream("\\\\sunny\\提出用\\JAVA\\773_才間 義雄_新Java\\01.A課題\\1回目\\exA\\商品一覧.csv"),"UTF-8"))){

            var rowIndex = 0;//行の管理
            String line;

            //リストにデータを格納
            while ((line = reader.readLine()) != null){
                csvDateSet(line,rowIndex);
                rowIndex++;
            }

            rowIndex = 0;//初期化
            String[] titleRow = csvDate.get(rowIndex++);//項目を取得
                    
            final var CODE_CULUMN = 0;//商品コードカラム
            final var NAME_CULUMN = 1;//商品名カラム
            final var UNIT_PRICE_CULUMN = 2;//単価カラム
            final var ORDER_CULUMN = 3;//販売数カラム
            final var AMOUNT_CULUMN = 4;//売上金額カラム
            
            while (rowIndex < csvDate.size()){

                //現在の行を格納
                String[] nowRows = csvDate.get(rowIndex);

                for (int culumn = 0; culumn < nowRows.length; culumn++){
                    String newSet = nowRows[culumn];

                    switch (culumn){//現在のカラムの判定

                        case CODE_CULUMN://0

                            if (isNumber(newSet)) {

                                var nowRowsNum = toInt(newSet);
                                newSet = String.format("%03d",nowRowsNum);
                                output(titleRow[culumn],newSet);

                            } else {
                                output(titleRow[culumn],newSet);
                            }
                            break;

                        case NAME_CULUMN://1
                            output(titleRow[culumn],newSet);
                            break;

                        case UNIT_PRICE_CULUMN://2
                            if (!nowRows[UNIT_PRICE_CULUMN].isEmpty()) {
                                var nowRowsNum = toInt(newSet);
                                newSet = NumberFormat.getNumberInstance().format(nowRowsNum);//カンマ区切り
                                output(titleRow[culumn],newSet);
                            } else {
                                newSet = "0";
                                output(titleRow[culumn],newSet);
                            }
                            break;

                        case ORDER_CULUMN://3
                            if (!nowRows[UNIT_PRICE_CULUMN].equals("0")){
                                newSet = inputOrderNum();
                            } else {
                                newSet = "0";
                                System.out.println("記入不可");
                            }
                            break;

                        case AMOUNT_CULUMN://4
                            var unitPriceNum = toInt(nowRows[UNIT_PRICE_CULUMN].replaceAll(",",""));
                            var orderNum = toInt(nowRows[ORDER_CULUMN]);
                            var totle = unitPriceNum * orderNum;
                            newSet = NumberFormat.getNumberInstance().format(totle);
                            output(titleRow[culumn],newSet);
                            break;
                    }
                    nowRows[culumn] = newSet;
                }
                System.out.println("---------------------------");
                csvDate.set(rowIndex,nowRows);
                rowIndex++;
            }
                writer();
        } catch(IOException e){
            
            e.printStackTrace();
        }
        
    }
    
    /* 受け取った値が数字か判定するメソッド
     * @param String
     * @return boolean
     */
    private static boolean isNumber(String input){

        input = input.replace("-", "");
        boolean result = true;

        //空欄ならfalse
        if (input.isEmpty()){
            result = false;
            return result;
        }

        //一文字ずつ先頭から数字であるか確認
        for(int i = 0; i < input.length(); i++) {

            //数字かどうか判定
            if (Character.isDigit(input.charAt(i))) {
                continue;
            } else {
                result =false;
                break;
            }
        }
        return result;
    }

    /* 全角数字を半角に変換するメソッド
     * @param String
     * @return String
     */
    public static String toHalfWidth(String input)throws IOException {

        StringBuilder sb = new StringBuilder(input);

        for (int i = 0; i < input.length(); i++) {

            //1文字ずつ抜き出す
            char set = input.charAt(i);

            //UTF-16での数字判定
            if (0xFF10 <= set && set <= 0xFF19) {
                sb.setCharAt(i, (char) (set - 0xFEE0));
            }

        }
        return sb.toString();
    }

    /* 受け取った値をintに変換して返す
     * @param String
    * @return int
    * @Exception NumberFormatException
     */
    private static int toInt(String num) throws IOException,NumberFormatException{
        var no = Integer.parseInt(num);
        return no;
    }

    /* 受け取った値を別CSVに書き込むメソッド
     * @param String 
     */
    private static void writer(){

        try (PrintWriter printer = new PrintWriter(new BufferedWriter(new FileWriter("売上.csv",false)))){

            //行の管理
            var rowIndex = 0;

            while (rowIndex < csvDate.size()){

                //現在の行の情報を取得
                String[] nowRows = csvDate.get(rowIndex);

                for (int i = 0; i < nowRows.length; i++){
                    //数字のカンマを区切られないようにする
                    if (nowRows[i].contains(",")){
                        nowRows[i] = "\"" + nowRows[i] + "\"";
                    }

                    //書き込み
                    printer.print(nowRows[i]);

                    //カンマを余分に増やさないように
                    if (i == nowRows.length - 1)break;

                    printer.print(",");
                }
                //改行
                printer.println();
                rowIndex++;
            }

            System.out.println("出力完了");
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }

    /* 入力してもらい、返す
     *@return String
     *@Exception IOException
     */
    private static String buffered() throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, "Shift-JIS"));
        String input = reader.readLine();

        return input;

    }

    /* 販売数を入力してもらうメソッド
     * @return String
     * @Exception IOException
     */
    private static String inputOrderNum() throws IOException{

        final int MAX_SALES_NUMBER = 100;
        final int MIN_SALES_NUMBER = 1;

        String input = "";
        
        while (true) {
        
            System.out.println("販売数を入力してください(1~100)");
        
            input = toHalfWidth(buffered());

            //1～100の数字を入力されるまで終わらない
            if (isNumber(input)){
        
                int inputNum = toInt(input);
        
                if (inputNum <= MAX_SALES_NUMBER && MIN_SALES_NUMBER <= inputNum){
                    break;
                }
            } 
        }
        return input;
    }

    /*csvDateリストに格納するメソッド
     * @param String int
     */
    private static void csvDateSet(String line, int rowIndex){
        
        ArrayList<String> list = new ArrayList<String>(Arrays.asList(line.split(",", -1)));

        //項目の追加
        if (rowIndex == 0){
            if (list.get(0).startsWith("\uFEFF"))list.set(0, list.get(0).substring(1));//BOM付きなら削除
            
            list.add(3,"販売数");
            list.add(4, "売上金額");    
        } else {
            list.add(3, "");
            list.add(4, "");
        }
        //listに格納
        csvDate.add(list.toArray(new String[0]));
    }

    /* 受け取った値を出力するメソッド
     * @param String String
     */
    private static void output(String titleRow, String newNowRow){
        System.out.println(titleRow + ":" + newNowRow);
    }

}