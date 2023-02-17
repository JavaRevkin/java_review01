/**
* 作成日2022.10.20
* 修正日2022.11.24
		2023.01.10
* @author 加納夕里夜
*JavaA課題3-5 カレンダー
*/
package exA.a05;

import java.text.DecimalFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.chrono.JapaneseDate;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.WeekFields;
import java.time.temporal.WeekFields;
import java.io.IOException;
import java.util.Calendar;
import java.util.Calendar ;
import java.util.GregorianCalendar ;
import java.util.Locale;

public class CalendarCalc_test{
	
/**
 * 入力された日付から該当月のカレンダーを作成
 * 入力された日付から年末まであと何週か表示
 * 週は日曜日～土曜日
 * 年末までの週数に入力された日付の週は含まない
	
 * @exception DateTimeException
 * @exception ParseException
*/
	
	
	public static void main(String[] args) {
		
		Format format = new DecimalFormat("00");
		try {
			Calendar cal = Calendar.getInstance();
			String strDate = args[0];
			StringBuilder sb = new StringBuilder(); 
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd"); 
		
			sb.append(strDate.substring(0,4) + "/"); 
			sb.append(strDate.substring(4,6) + "/");
			sb.append(strDate.substring(6,8));
			sdf.parse(sb.toString());
			
			String[] delimiter = sb.toString().split("/");
			
			int year = Integer.parseInt(delimiter[0]);
			int month = Integer.parseInt(delimiter[1]);
			int day = Integer.parseInt(delimiter[2]);
			
			cal.set(year, month, day);
			Calendar cal2 = Calendar.getInstance();
			int lastDayOfMonth = cal.getActualMaximum(Calendar.DATE);
			cal2.set(year, month,lastDayOfMonth);
			
			// 1か月に何週間あるか 指定日が月の何週目か
			int weeks = cal2.get(Calendar.DAY_OF_WEEK_IN_MONTH) ;
			System.out.println("カレンダー作成日:" + sb.toString()); 
			int numOfWeeks = (int)LocalDate.of(year, 1, 1).datesUntil(LocalDate.of(year, 12, 31), Period.ofDays(7)).count() ; 
			
			int weekOfyear = LocalDate.of(year,month,day).get(WeekFields.of(Locale.JAPANESE).weekOfWeekBasedYear());
			int weekOfmonth = LocalDate.of(year,month,day).get(WeekFields.of(Locale.JAPANESE).weekOfMonth());
			year = Integer.parseInt(delimiter[0]);
			
			// 楊さんの教えてくれたコード
			cal.set(Calendar.YEAR, 2022);
	        System.out.println("test" + cal.getActualMaximum(Calendar.WEEK_OF_YEAR));
			
			cal.set(Calendar.YEAR, year);
	        System.out.println("testtest" + cal.getActualMaximum(Calendar.WEEK_OF_YEAR));
			
			
			if(year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
				System.out.println(year + "年" + (format.format(month)) + "月の週数:" + weeks);
				System.out.println("年末までの週数:" + (numOfWeeks - weekOfyear));
			} else {
				if(month == 1) {
					System.out.println(year + "年" + (format.format(month))  + "月の週数:" + (weeks + 1));
					System.out.println("年末までの週数:" + ((numOfWeeks - weekOfyear) + 1));
				} else {
					System.out.println(year + "年" + (format.format(month))  + "月の週数:" + weeks);
					System.out.println("年末までの週数:" + ((numOfWeeks - weekOfyear) + 1));
				}
			}
		} catch(DateTimeException e) {
			System.out.println("入力した日は存在しません");
		} catch(ParseException e) {
			System.out.println("年月日をいれてください。（例：20200401）");
		}
	}
	public static long getdayOfWeek(int year, int month, int day) {
		
		// 1年の週数を数える
		int numOfWeeks = (int)LocalDate.of(year, 1, 1).datesUntil(LocalDate.of(year, 12, 31), Period.ofDays(7)).count() ; 
		// 配列で入れた日付が1年で何週目か求める
		int numOfWeeks2 = (int)LocalDate.of(year, 1, 1).datesUntil(LocalDate.of(year,month,day), Period.ofDays(7)).count() + 1; 		
		// 配列で入れた日付から1年の残りの週数
		return numOfWeeks - numOfWeeks2  ; 
	}
}























/*

	https://teratail.com/questions/119866
	
https://teratail.com/questions/119866 参考
https://magazine.techacademy.jp/magazine/27839
https://www.web-development-kb-ja.site/ja/java/java%E3%81%A71%E5%B9%B4%E3%81%AE%E5%90%88%E8%A8%88%E9%80%B1%E3%82%92%E8%A6%8B%E3%81%A4%E3%81%91%E3%82%8B%E6%96%B9%E6%B3%95%E3%81%AF%EF%BC%9F/814169725/

	https://www.web-development-kb-ja.site/ja/java/java%E3%81%A71%E5%B9%B4%E3%81%AE%E5%90%88%E8%A8%88%E9%80%B1%E3%82%92%E8%A6%8B%E3%81%A4%E3%81%91%E3%82%8B%E6%96%B9%E6%B3%95%E3%81%AF%EF%BC%9F/814169725/
	
	
boolean is53weekYear = LocalDate.of(year, 1, 1).getDayOfWeek() == DayOfWeek.THURSDAY ||
        LocalDate.of(year, 12, 31).getDayOfWeek() == DayOfWeek.THURSDAY;
int weekCount = is53weekYear ? 53 : 52;	
	
	
	
	
	/*	//---西暦year年は閏年か---//
		static int isLeap(int year) {
			return(year %4 == 0 && year % 100 != 0 || year%400 ==0) ? 1: 0;
		}*/
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


/*	boolean check = false;
		
		if(year % 4 == 0){
			if((year % 100) ==0){
				if((year % 400) == 0){
					check = true;
				}
			} else {
				check = true;
			}
		}
		System.out.println
		*/
		
		
		// うるう年だったら+1
	/*	if(numOfWeeks < 366){
			numOfWeeks + 1;
		} 
		*	
		
	/*	// 各月の週数
	static int[][] mweeks = {
		// 平年
		{
			5,4,5,5,5,5,5,5,5,5,5,5
		}
		// うるう年
		{
			5,5,5,5,5,5,5,5,5,5,5,5
		}
	}
	// 西暦year年は閏年か
	static int isLeap(int year){
		return(year %4 == 0 && year % 100 ! = 0 || year %400 == 0) ? 1:0;
	}
	
		// 年内残りの週数を求める
	public static int leftWeekOfYear(int y,int m,int d){
		
		// 今月の残りの週数
		int remweeks = mweeks[isLeap(y)][m - 1] - d;
		// 12月～（ｍ+1）月の日数を加える
		for(int i = 12; i > m; i--){
			remweeks += mweeks[isLeap(y)][i - 1];
		}
		return remweeks;
	}
	
	// 西暦ｙ年ｍ月d日の年内の経過日数を求める
	static int dayOfYear(int y, int m, int d) {
		// 日数
		int weeks = w;
		// １月～（ｍ-1）月の日数を加える
		for(int i = 1; i < m; i++){
			// isLeap()は閏年であれば1，平年であれば0
			weeks += mweeks[isLeap(y)][i-1];
		}
		return weeks;
	}
			*/	
		
		
		
		
		
		




/*	static int isLeap(int year){//閏年
		return(year %4 == 0 && year % 100 != 0 || year%400 ==0 ) ? 1:0;
	}
	
	
	static int weekOfYear(int y, int m, int d, int w){ 
		int weeks = w; //週数
		
		for(int i = 1; i < m; i++){ 
			days += mdays[isLeap(y)][i - 1];
		}
		return days;
	}
	static int leftWeekOfYear(int y, int m, int d, int w){
		int remweeks = mweeks[isLeap(y)][m] - w; //今月の残りの週数
		for(int i = 12; i > m; i--){ //12月～（ｍ+1）月の日数を加える
			remweeks += mweeks[isLeap(y)][i - 1];
		}
		return remweeks;
	}
	
	public static void main(String[] args){
		Scaner scan = new Scanner(System.in);
		int retry;
		
		System.out.println("年内の経過日数と残り日数を求めます、");
			
		do {
			System.out.print("年：");
			int year = scan.nextInt(); //年
			System.out.print("月：");
			int month = scan.nextInt(); //月
			System.out.print("日：");
			int day = scan.nextInt(); //日
			System.out.printf("年内で%d日目です\n", dayOfYear(year,month,day));
			System.out.printf("年内の残りは%d日です\n", leftDayOfYear(year,month,day));
			System.out.print("もう一度しますか（1...はい/0...いいえ):");
			retry = scan.nextInt();
		}
		while(retry == 1);
	}
*/


	
		
