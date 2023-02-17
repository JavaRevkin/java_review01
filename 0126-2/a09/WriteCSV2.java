 /** 
 * 作成日2022.10.31
 * 修正日2022.11.24-1/20
 * @author 加納夕里夜
 *JavaA課題3-9 CSV ファイルの出力
 */

package exA.a09;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Scanner;
import java.util.StringJoiner;



public class WriteCSV2{
	
 /**
 *CSV ファイル「商品一覧.csv」を読み込み、画面で入力した販売数を元に
 *「販売数」と「売上金額」の２項目を追加した「売上.csv」を作成
 *出力する CSV の金額項目は３桁カンマ区切りした状態で出力
 *単価が空の商品以外全ての商品の販売数を画面で順に入力
 *未入力は許可しない
 *入力できる販売数は 1～100 の範囲
 *@param number_of_sales 販売数,tanka 単価,sum_price 売上金, 
 *@exception NumberFormatException
 */
	
	public static void main(String args[]) throws FileNotFoundException,UnsupportedEncodingException {
		
		File file = new File("exA/a09/商品一覧.csv");

		BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(new FileInputStream(file),"Shift-JIS"));
		BufferedReader bufferedreader2 = new BufferedReader(new InputStreamReader(System.in));
			
		try (FileOutputStream fileoutputstream = new FileOutputStream("exA/a09/売上.csv");
			OutputStreamWriter outputstreamwriter = new OutputStreamWriter(fileoutputstream,"Shift-JIS")){
			
			String line;
			// CSVの中身
			String[] values = null; 
			//読み込み行数の管理
			int i = 0; 
			int tanka = 0;
			// 売上金の変数
			int sum_price = 0;
			
			while ((line = bufferedreader.readLine()) != null) {  
				// 先頭行は列名の取得、CSVに書き込み
				if (i == 0) {
					values  = line.split(",");
					for (String value : values) {
						// 商品コード,商品名,単価,
							outputstreamwriter.write("\"" + value + "\"");
							outputstreamwriter.write(",");
					}
					outputstreamwriter.write("\"" + "販売数" + "\"");
					outputstreamwriter.write(",");
					outputstreamwriter.write("\"" + "売上金額" + "\"");
					outputstreamwriter.write("\r\n");
				} else {
					int number = 0;
					String[] takevalues = splitLineWithComma(line);
					// 販売数の変数
					int number_of_sales = 0; 
					int j = 0;
					String productcode = "商品コード";
					String productname = "商品名";
					String price = "単価";
					
					for (String name : takevalues) {
						if (values[number].equals(productcode)) {
							if ( name.matches("\\d{1,9}")) {
								String zerotume = String.format("%03d",(Integer.parseInt(name)));
								
								outputstreamwriter.write("\"" + zerotume + "\"");
								System.out.println(values[number] + ":" + zerotume);
							} else {
								outputstreamwriter.write("\"" + name + "\"");
								System.out.println(values[number] + ":" + name);
							}
						}
						if (values[number].equals(productname)) {
							outputstreamwriter.write("\"" + name + "\"");
							System.out.println(values[number] + ":" + name);
						}
						if (values[number].equals(price)) {
							if (name.matches("\\d{1,9}")) {
								String attachcomma = String.format("%,d",(Integer.parseInt(name)));
								
								//String joincomma = String.join("," , attachcomma);
							
								
								outputstreamwriter.write("\"" + attachcomma + "\"");
								System.out.println(values[number] + ":" + attachcomma);
							} else {
								outputstreamwriter.write("\"" + name + "\"");
								System.out.println(values[number] + ":" + name);
							}
						}
					
						outputstreamwriter.write(",");
						number++;
					}
					// 単価が空文字か判定
					switch ( ":" + takevalues[number - 1] ) {
						case  ( ":" + ""): 
						outputstreamwriter.write("\r\n");
						break;
						default:

						while (true) {
							System.out.println("販売数：");
							number_of_sales = Integer.parseInt(bufferedreader2.readLine());
							System.out.println("[" + number_of_sales + "]");
						
							String product_num = Integer.toString(number_of_sales);
							// outputstreamwriter.write( Integer.toString(number_of_sales));
							outputstreamwriter.write("\"" + product_num + "\"");
							outputstreamwriter.write(",");
							if (number_of_sales >= 1 && number_of_sales <= 100) {
								// 単価×販売数
								tanka = Integer.parseInt(takevalues[2].replace(",",""));
								sum_price = tanka * number_of_sales;
								NumberFormat numberformat2 = NumberFormat.getInstance();
								outputstreamwriter.write("\"" + (numberformat2.format(sum_price) ) + "\"");
								outputstreamwriter.write("\r\n");
								break;
							} else {
								System.out.println("整数1～100を入力してください");
							}
						}
					}
				}
				i++;
			}
			System.out.println("売上.csv を出力しました。");
		} catch(IOException e) {
			System.out.println();
		} catch(NumberFormatException e) {
			System.out.println("整数1～100を必ず入力してください、最初からやり直してください。");
		} finally {
		}
	}
	 /** 
     * カンマ区切りで行を分割し、文字列配列を返す。
     * 
     * ※下記について、アンエスケープ後の文字列を返す。
     * 1. 値にカンマ(,)を含む場合は,値の前後をダブルクオート(")で囲う
     * 2. ダブルクオート(")は，2つのダブルクオートに置換する("")
     * 
     * */
	private static String[] splitLineWithComma(String line) {
        // 分割後の文字列配列
		String[] arr = null;
 
		try {
            // １、「"」で囲まれていない「,」で行を分割する。
			Pattern cPattern = Pattern.compile(",(?=(([^\"]*\"){2})*[^\"]*$)"); 
			String[] cols = cPattern.split(line, -1);  
             
			arr = new String[cols.length];  
			for (int i = 0, len = cols.length; i < len; i++) {  
				String col = cols[i].trim();  
                 
				// ２、最初と最後に「"」があれば削除する。
				Pattern sdqPattern = Pattern.compile("^\"|\"$"); 
				Matcher matcher = sdqPattern.matcher(col);  
				col = matcher.replaceAll("");  
      
                // ３、エスケープされた「"」を戻す。 
				Pattern dqPattern = Pattern.compile( "\"\""); 
				matcher = dqPattern.matcher(col);  
				col = matcher.replaceAll("\"");  
                 
				arr[i] = col;  
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
         
		return arr; 
	}
}