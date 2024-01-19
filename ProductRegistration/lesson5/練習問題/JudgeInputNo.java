import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class   JudgeInputNo
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
        
        if (num >35) {
           if (num < 35 + 10) {
                System.out.println("少し大きい");
            }else{
                System.out.println("大きい");
            }
        }else if (num < 35){
            if (num  > 35 - 10) {
                System.out.println("少し小さい");
            }else{
                System.out.println("小さい");
            } 
        }else{
            System.out.println("正解");
        }
        
    }
}