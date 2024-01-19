import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class   LeapYear
{
    public static void main(String arg[])
    {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(isr);
        int year = 0;
        String tmp = null;
        
        try {
            tmp = reader.readLine();
            year = Integer.parseInt(tmp);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        if (year % 4 == 0) {
           if (year % 100 == 0) {
                if (year % 400 == 0) {
                    System.out.println(year + "年はうるう年です");
                }
                else
                {
                    System.out.println(year + "年はうるう年ではありません。");
                }
            }
            else
            {
                System.out.println(year + "年はうるう年です");
            }
         } 
         else 
         {
            System.out.println(year + "年はうるう年ではありません。");
         }
        
    }
}