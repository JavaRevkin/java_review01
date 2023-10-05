import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ClosingDate {
    public static final int CLOSING_DAY = 20;
    
    public static void main(String[] args) {
        Calendar currentDate = Calendar.getInstance();
        System.out.println(new SimpleDateFormat("現在日付：yyyy年MM月dd日").format(currentDate.getTime()));
        if (currentDate.get(Calendar.DATE) > CLOSING_DAY) currentDate.add(Calendar.MONTH, 1);
        System.out.println(new SimpleDateFormat("年月度：yyyy年MM月度").format(currentDate.getTime()));
    }
}