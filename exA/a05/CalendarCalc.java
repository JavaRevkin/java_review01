import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CalendarCalc {
    public static void main(String[] args) {
        Calendar creationDate = Calendar.getInstance();
        creationDate.set(Calendar.YEAR, Integer.parseInt(args[0].substring(0, 4)));
        creationDate.set(Calendar.MONTH, Integer.parseInt(args[0].substring(4, 6)) - 1);
        creationDate.set(Calendar.DATE, Integer.parseInt(args[0].substring(6, 8)));
        System.out.println(new SimpleDateFormat("カレンダー作成日：yyyy/MM/dd").format(creationDate.getTime()));
        System.out.println(new SimpleDateFormat("yyyy年MM月の週数：").format(creationDate.getTime())
            + creationDate.getActualMaximum(Calendar.WEEK_OF_MONTH)
        );
        Calendar newYearsEve = Calendar.getInstance();
        newYearsEve.set(creationDate.get(Calendar.YEAR), 11, 31);
        System.out.println("年末までの週数：" + (
            creationDate.get(Calendar.WEEK_OF_YEAR) > 1 || creationDate.get(Calendar.MONTH) < 11
            ? creationDate.getActualMaximum(Calendar.WEEK_OF_YEAR) - creationDate.get(Calendar.WEEK_OF_YEAR)
                + (newYearsEve.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY ? 1 : 0)
            : 0
        ));
    }
}