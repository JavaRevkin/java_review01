public class CalcTime
{
    public static void main(String arg[])
    {
        int time = 680;
        int hour = time / 60;
        int min = time % 60;
        
        System.out.println(time + "分は" + hour + "時間" + min + "分です。");
    }
}