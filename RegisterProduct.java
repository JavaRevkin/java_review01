package exA.a10;

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.nio.charset.Charset;

/**
 * 画面で入力した件数分、連続して商品情報（商品名、単価）を入力し、入力後に一覧表示する
 *   商品コードは1から自動採番し、同名の商品の登録は不可とする。また、商品コードは1から開始とする
 *   商品情報は[Product.java]にクラスを作成し、インスタンス生成時に値を設定できるものとする。また、メソッドはゲッターのみ作成する。
 *   入力できる金額は1～999,999の範囲
 * 
 * @author 才間
 */

public class RegisterProduct{

    //商品名の重複を許さないために名前をキーとして値に単価を一時的に保持
    private static LinkedHashMap<String,String> tmpProductDate = new LinkedHashMap<String,String>();
    //商品情報を保持するリスト
    private static ArrayList<Product> productDateList = new ArrayList<Product>();

    public static void main(String[] args){

        while (true){
        
            try {

                System.out.println("商品登録したい件数を入力してください");

                //追加したい件数
                var loopNum = toHalfWidth(input());
                //エラーメッセージの管理
                String errorMsg = null;

                if (hasInt(loopNum)){

                    var loopLength = toInt(loopNum);

                    if (0 < loopLength){
                        //件数だけ追加
                        addTmpProductDate(loopLength);
                        generateAddProduct();
                        break;
                    } else {
                       errorMsg = "整数(1以上)";
                    }

                } else {
                    errorMsg = "件数(追加したい数)を入力してください";
                }
                
                System.out.println(errorMsg + "を入力してください");
        
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NumberFormatException e){
                System.out.println("正しく入力してください" + "\n" + "初めからやり直しです" + "\n");
            }

        }

        System.out.println("商品一覧");
        for (Product key : productDateList){
            String code = format(key.getCode());
            String name = format(key.getName());
            String price = format(key.getPrice());
            System.out.println(code + name + price);
        }
    
    }

    /* 入力してもらうメソッド
     * @return String
     * @exception IOException
     */
    private static String input() throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, "Shift-JIS"));
        String input = reader.readLine();
        return input;
    }

    /* intに変換するメソッド
     * @param String
     * @return int
     * @exception NumberFormatException
     */
    private static int toInt(String num) throws NumberFormatException{
        var no = Integer.parseInt(num);
        return no;
    }

    /*  受け取った値が数字になりえるか判定するメソッド
     * @param String
     * @return boolean
     */
    private static boolean hasInt(String num){
        return num.matches("[\\d]+");
    }
 
    /* 全角数字を半角に変換するメソッド
     * @param String
     * @return String
     */
    public static String toHalfWidth(String input) {

        StringBuilder sb = new StringBuilder(input);
        
        for (int i = 0; i < input.length(); i++) {

            char set = input.charAt(i);

            //UTF-16での数字判定
            if (0xFF10 <= set && set <= 0xFF19) {
                sb.setCharAt(i, (char) (set - 0xFEE0));
            }

        }
        return sb.toString();
    }

    /* 受け取ったStringのバイト数を出してint分で表示位置を合わせる
    *@param String int
    *@return String
    */
    private static String format(String date){
        var length = 20;
        int byteDiff = (getByteLength(date, Charset.forName("UTF-8"))-date.length()) / 2;
        return String.format("%-"+(length-byteDiff)+"s", date);
    }

    /* 受け取ったStringのバイト数を返す
    *@param String Cherset
    *@return int
    */
    private static int getByteLength(String date, Charset charset) {
        return date.getBytes(charset).length;
    }

    /* mapにある要素でProductを生成し、listに追加するメソッド
     * 
     */
    private static void generateAddProduct(){

        //現在の件数番の管理
        var nowLoopNum = 0;

        for (String key : tmpProductDate.keySet()){

            Product product;

            if (nowLoopNum == 0){
                //項目の行
                product = new Product("商品コード", key, tmpProductDate.get(key));
            } else {
                var codeSet = String.format("%03d",nowLoopNum);
                product = new Product(codeSet, key, tmpProductDate.get(key));
            }

            //リストに格納
            productDateList.add(product);

            nowLoopNum++;
        }
    }

    /* mapに商品名をキーとして重複の判定と単価を件数だけ追加するメソッド
     * @param int
     */
    private static void addTmpProductDate(int loopLength) throws IOException{

        //デフォルトで添え字0に情報を保持
        tmpProductDate.put("商品名", "単価");

        //現在の件数番の管理
        var nowLoopNum = 1;
        //自動的に商品コードを追加管理
        String codeSet = null;
        //入力された商品名
        String inputName = null;
        //入力された単価
        String inputPrice = null;

        while (nowLoopNum <= loopLength){

            //商品コードを自動採番
            codeSet = String.format("%03d",nowLoopNum);

            System.out.println("--------------------------------");
            System.out.println("商品コード：" + codeSet);
            System.out.println("商品名");
            inputName = input();

            //すでに同名の商品名がないか判定
            if (!tmpProductDate.containsKey(inputName)){
           
                while (true){

                    //エラーメッセージの管理
                    String errorMsg = null;
                
                    final var MAX_PRICE = 999999;
                    final var MIN_PRICE = 1;
                    System.out.println("単価");
                    inputPrice = toHalfWidth(input());

                    if (hasInt(inputPrice)){

                        var price = toInt(inputPrice);
                    
                        if (MIN_PRICE <= price && price <= MAX_PRICE){
                            //ALLCOMPLETE
                            break;
                        } else {
                            errorMsg = "単価(" + MIN_PRICE + "~" + MAX_PRICE + ")";
                        }
                    
                    } else {
                        errorMsg = "整数";
                    }
                    //エラーメッセージの出力
                    System.out.println(errorMsg + "を入力してください");
                }

            } else {
                System.out.println("同じ商品名がございますので追加できません");
                continue;
            }
        
            //情報を一時保管
            tmpProductDate.put(inputName, inputPrice);
            nowLoopNum++;
        }
    }

}