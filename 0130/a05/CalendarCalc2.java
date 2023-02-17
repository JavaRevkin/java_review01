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
import java.time.temporal.WeekFields;
import java.io.IOException;
import java.util.Calendar;
import java.util.Calendar ;
import java.util.GregorianCalendar ;
import java.util.Locale;


public class CalendarCalc2{
	
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
			Calendar calendar = Calendar.getInstance();
			String getDate = args[0];
			StringBuilder stringbuilder = new StringBuilder(); 
			SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyy/MM/dd"); 
		
			sb.append(strDate.substring(0,4) + "/"); 
			sb.append(strDate.substring(4,6) + "/");
			sb.append(strDate.substring(6,8));
			sdf.parse(sb.toString());
			
			String[] delimiter = sb.toString().split("/");
			
			int year = Integer.parseInt(delimiter[0]);
			int month = Integer.parseInt(delimiter[1]);
			int day = Integer.parseInt(delimiter[2]);
			
			System.out.println("カレンダー作成日:" + sb.toString());
			
			