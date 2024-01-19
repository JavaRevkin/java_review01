import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class   MatchNo
{
    public static void main(String arg[])
    {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(isr);
        int first = 0;
        int second = 0;
        int third =0;
        int point = 0;
        String tmp = null;
        
        try {
            tmp = reader.readLine();
            first = Integer.parseInt(tmp);
            tmp = reader.readLine();
            second = Integer.parseInt(tmp);
            tmp = reader.readLine();
            third = Integer.parseInt(tmp);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        if(first == 9){
            point += 2;
        }else if(first == 2 || first == 5){
            point += 1;
        }
        
        if(second == 2){
            point += 2;
        }else if(second == 9 || second == 5){
            point += 1;
        }
        
        if(third == 5){
            point += 2;
        }else if(third == 9 || third == 2){
            point += 1;
        }
        
        System.out.println("点数は" + point + "点です。");
    }