import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class   EvenOrOdd
{
    public static void main(String arg[])
    {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(isr);
        int num = 0;
        String tmp = null;
        
        try {
            tmp = reader.readLine();
            num = Integer.parseInt(tmp);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        if(num % 2 == 0){
            System.out.println("偶数です");
        }
        else{
            System.out.println("奇数です");
        }
        
    }
}