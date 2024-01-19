import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;
import java.lang.Math;

public class  Omikuji
{
    public static void main(String arg[])
    {
        
        int kuji = 0;
        kuji = (int)Math.ceil(Math.random() * 6);
        
        
        
        System.out.print("あなたの運勢は");
        
        
        switch(kuji){
            case 1:
                System.out.print("大吉");
                break;
            case 2:
                System.out.print("中吉");
            case 3:
                System.out.print("吉");
                break;
            case 4:
                System.out.print("小吉");
                break;
            case 5:
                System.out.print("凶");
            case 6:
                System.out.print("大凶");
                break;
            
        }
        
        System.out.println("です。");
    }
}