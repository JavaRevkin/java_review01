/**
* 作成日2022.10.31
* 修正日2022.11.24
		2023.01.06
* @author 加納夕里夜
*JavaA課題3-9 CSV ファイルの出力
*/

package exA.a09;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;


public class WriteCSV{
	
/**
*CSV ファイル「商品一覧.csv」を読み込み、画面で入力した販売数を元に
*「販売数」と「売上金額」の２項目を追加した「売上.csv」を作成
*出力する CSV の金額項目は３桁カンマ区切りした状態で出力
*単価が空の商品以外全ての商品の販売数を画面で順に入力
*未入力は許可しない
*入力できる販売数は 1～100 の範囲
*@paramnum 販売数,tanka 単価,sum 売上金, number 商品コード
*@exception NumberFormatException
*/
	
	public static void main(String args[]) {
		
		try {
			
			BufferedReader br = null;
			String fileName = "exA/a09/商品一覧ANSI.csv";
			br = new BufferedReader(new FileReader(fileName));
			// テスト InputStreamReader(new FileInputStream(fileName), "UTF-8"));
			br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
			// 107行目のため 数量制限
		/*	BufferedReader br2 = null;
			br2 =  new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));*/
			
			File file = new File("exA/a09/売上.csv");
			FileWriter filewriter = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(filewriter);
			// PrintWriter pw = new PrintWriter(bw);
		 	 PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true),"Shift-JIS")));

			StringBuilder sb = new StringBuilder();
		
			String line;
			String[] data = null; 
		//	 テスト String[] data2 =null; 
			int i = 0;
			int kazu = 0;
			int tanka = 0;
			int sum = 0;

			while ((line = br.readLine()) != null) {  // テスト = Leadline
				
				if (i == 0) {
					data  = line.split(",");
					
					for (String name2 : data) {
						// 商品コード,商品名,単価,
						bw.write(name2);
						bw.write(",");
						
						//  テスト bw.newLine();
					}
					bw.write("\r\n");
				} else {
					int number = 0;
					String[] data2 = splitLineWithComma(line);
					int num = 0; 
					
					for (String name : data2) {
						System.out.println(data[number] + ":" +  name);
						number++;
						
						bw.write(name); 
						bw.write(",");
						//  テスト bw.newLine();
					}
					
					//  テスト System.out.println(data2[2]);
					switch (data[number - 1]  + ":" + data2[number - 1] ) {
						case  ("単価" + ":" + ""): 
						break;
						default:
						while (true) {
						
							num = Integer.parseInt(br.readLine());
						
							bw.write(num);
							bw.write(",");
							//  テスト bw.newLine();
						
							if (1 <= num && num <= 100) {
								//  テスト System.out.println("1");
								// 単価×販売数
								tanka = Integer.parseInt(data2[2].replace(",",""));
								sum = tanka * num;
						 		 // テスト System.out.println(sum);
						 	
								bw.write(sum);
								bw.write(",");
								bw.write("\r\n");
								break;
							
							} else {
								System.out.println("整数1～100を入力してください");
							}
						}
					}
				}
				i++;
			}
			
		/* テスト	public static void exportCsv(int[] number,String[] name2,String[] data2, int[] sum){
			for(int k = 0; k < number.length; k++){
				pw.print(name2);
				pw.print(",");
				pw.print(data2[2]);
				pw.print(",");
				pw.print(sum);
				pw.println();
				
			}
			}
			*/
		
			bw.close();
			System.out.println("売上.csv を出力しました。");
		} catch(IOException e) {
			System.out.println();
		
		} catch(NumberFormatException e) {
			System.out.println("整数1～100を必ず入力してください、最初からやり直してください。");
		} finally {
			// テストbw.close();
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
                Pattern sdqPattern = 
                    Pattern.compile("^\"|\"$"); 
                Matcher matcher = sdqPattern.matcher(col);  
                col = matcher.replaceAll("");  
      
                // ３、エスケープされた「"」を戻す。 
                Pattern dqPattern = 
                    Pattern.compile( "\"\""); 
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