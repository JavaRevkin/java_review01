import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class  EtoYear
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
        
        System.out.print(year + "年は");
        year = year % 12;
        
        switch(year){
            case 0:
                System.out.print("子");
                break;
            case 1:
                System.out.print("丑");
                break;
            case 2:
                System.out.print("寅");
            case 3:
                System.out.print("卯");
                break;
            case 4:
                System.out.print("辰");
                break;
            case 5:
                System.out.print("巳");
            case 6:
                System.out.print("午");
                break;
            case 7:
                System.out.print("未");
                break;
            case 8:
                System.out.print("申");
            case 9:
                System.out.print("酉");
                break;
            case 10:
                System.out.print("戌");
                break;
            case 11:
                System.out.print("亥）");
        
        }
        
        System.out.println("年です。");
    }
}