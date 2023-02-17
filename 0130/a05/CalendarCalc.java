 /**
 * 作成日2022.10.20
 * 修正日2022.11.24
		2023.01.10
		2023.01.20
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
import java.io.IOException;
import java.util.Calendar;
import java.util.Calendar ;
import java.util.GregorianCalendar ;
import java.util.Locale;
import java.time.temporal.TemporalAdjusters;
//import org.joda.time.DateTime;
//import java.util.Date;


public class CalendarCalc{
	
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
			Calendar calendar = Calendar.getInstance();
			int lastDayOfMonth = cal.getActualMaximum(Calendar.DATE);
			
			System.out.println("カレンダー作成日:" + sb.toString());
			
			
			final var adjuster = TemporalAdjusters.lastDayOfMonth();
			final var date1 = LocalDate.of(year,month,day);
			final var last_day_of_month = date1.with(adjuster);
			final var weekField = WeekFields.SUNDAY_START.weekOfMonth();

			int numOfWeeks = (int)LocalDate.of(year, 1, 1).datesUntil(LocalDate.of(year, 12, 31), Period.ofDays(7)).count() ; 
			int weekOfyear = LocalDate.of(year,month,day).get(WeekFields.of(Locale.JAPANESE).weekOfWeekBasedYear());
			int weekOfyeartest = cal.get(Calendar.WEEK_OF_YEAR);
		/*	int testnumOfWeeks = (int)LocalDate.of(year, 1, 1).datesUntil(LocalDate.of(year, 12, 31), Period.ofDays(1)).count() ;
			System.out.println("testtesttest" + testnumOfWeeks);*/
			
			// 楊さんの教えてくれたコード 年の週数
		/*	cal.set(Calendar.YEAR, year);
	        System.out.println("test" + cal.getActualMaximum(Calendar.WEEK_OF_YEAR));
			System.out.println("test" + "年末までの週数:" + (cal.getActualMaximum(Calendar.WEEK_OF_YEAR) - weekOfyear));*/
			
		/*	cal.set(Calendar.YEAR, year);
	        System.out.println("testtest" + cal.getActualMaximum(Calendar.WEEK_OF_YEAR));
			System.out.println("testtest" + "年末までの週数:" + (cal.getActualMaximum(Calendar.WEEK_OF_YEAR) - weekOfyear));*/
			
			
			if(year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
				System.out.println(year + "年" + (format.format(month)) + "月の週数:" + (last_day_of_month.get(weekField)));
				//System.out.println("年末までの週数:" + (cal.getActualMaximum(Calendar.WEEK_OF_YEAR) - weekOfyear));
				System.out.println("年末までの週数:" + (numOfWeeks - weekOfyear));
			} else {
				if(month == 1) {
					System.out.println(year + "年" + (format.format(month))  + "月の週数:" + (last_day_of_month.get(weekField)));
					//System.out.println("年末までの週数:" + (cal.getActualMaximum(Calendar.WEEK_OF_YEAR) - weekOfyear));
					// +1は一年の日数がPeriod.ofDaysだと1日少ないから
					System.out.println("年末までの週数:" +  ((numOfWeeks - weekOfyear) +1));
				} else {
					System.out.println(year + "年" + (format.format(month))  + "月の週数:" + (last_day_of_month.get(weekField)));
					//System.out.println("年末までの週数:" + (cal.getActualMaximum(Calendar.WEEK_OF_YEAR) - weekOfyear));
					System.out.println("年末までの週数:" +  ((numOfWeeks - weekOfyear) +1));
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

