import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class  Bmi
{
    public static void main(String arg[])
    {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(isr);
        double bmi = 0;
        double weight = 0;
        double height = 0;
        String tmp = null;
        
        try {
            tmp = reader.readLine();
            weight = Float.parseFloat(tmp);
            tmp = reader.readLine();
            weight = Float.parseFloat(tmp);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        bmi = weight / height * height;
        
        if (bmi > 18.5) {
           System.out.println("やせてます");
        }else if (bmi < 25){
            System.out.println("正常です");    
        }else if(bmi <  30){
            System.out.println("太ってます");
        }else{
            System.out.println("肥満です");
        }
    }
}