package a01;

import java.util.Scanner;

/**
 * 電話番号の項目別表示。 
 * 
 * @author ダウド・カーン

//この問題の入力 
//プログラムを実行するコマンド。 : "java Tel.java" or "java Tel.java 052-937-0201"
//プログラムを実行するための入力 '052-937-0201'
//市外局番： 052
//市内局番:  937
//加入者番号： 0201
*/
public class Tel {

	public static void main(String[] args) 
	{
		String[] arr = null;
		String tele_number = null;
		boolean tester = false;
		Scanner scanner = new Scanner(System.in);

		//これは、2 つの「-」文字を含む 12 文字の長さの文字列を 1 つ取得するためのものです。
		String regexValue = "^(?:\\d{10}|\\d{3}-\\d{4}-\\d{4}|\\d{3}-\\d{3}-\\d{4}|\\d{2}-\\d{4}-\\d{4}|\\d{4}-\\d{4}-\\d{2}|\\d{4}-\\d{2}-\\d{4})$";

		// 「java Tel.java 052-937-0201」のようなコマンドの場合
		if (args.length > 0 && args[0].length() == 12 && args[0].matches(regexValue)) 
		{
			tele_number = args[0];
			arr = splitter(tele_number);

		} 

		// 「12345-85965-56974」のような間違った電話形式の入力
		else 
		{
			System.out.println("このように押してください: 000-000-0000");
			tele_number = scanner.nextLine();
			arr = splitter(tele_number);
		}

		// 「if」ステートメントは正しい入力用です-プログラムの実行後
		while (!tester) {
			if (arr.length == 3 && tele_number.length() == 12 && tele_number.matches(regexValue)) 
			{
				tester = true;
				scanner.close();
			} 

			// 「else」ステートメントは間違った入力用です-プログラムの実行後
			else 
			{
				System.out.println("このように押してください: 000-000-0000");
				tele_number = scanner.nextLine();
				arr = splitter(tele_number);
			}
		} 

		// 要件に従って分割された電話番号を表示します
		System.out.println("市外局番： " + arr[0]);
		System.out.println("市内局番: " + arr[1]);
		System.out.println("加入者番号： " + arr[2]);
	}

	/**
	 * (このメソッドは、「-」が見つかったときに「文字列」を分割します)
	 * @param args (このメソッドは、電話番号である 1 つの文字列引数を取ります。)
	 * @return (このメソッドは文字列値を返します[文字列番号]。) 
	 * @exception (このメソッドは例外をスローしません。)
	 **/

	public static String[] splitter(String tel) 
	{
		String[] arr = new String[3];
		String s = "";
		int arrLen = 0;

		// 数を分割するため
		for (int i = 0; i < tel.length(); i++) 
		{
			// インデックス 0 から「"-" 文字の前」までが s に格納されます
			if (tel.charAt(i) != '-')
			{
				s += tel.charAt(i);
			}

			// 最後に抽出されたピースがここに保存されます
			if (i == tel.length() - 1) 
			{
				arr[arrLen] = s;
				arrLen += 1;
			} 
			
			// 「-」を取得した後に値を保存します
			else if (tel.charAt(i) == '-') 
			{
				arr[arrLen] = s;
				arrLen += 1;
				s = "";
			}
		}
		return arr;
	}

}