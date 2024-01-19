import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class VarInput
{
    public static void main(String arg[])
    {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(isr);
        
        String str = null;
        try {
            str = reader.readLine();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        System.out.println("入力した文字列は「"+ str + "」です。");
    }
}