package a01;


public class TEl {
    public static void main(String[] args) {
        String[] tels = args[0].split("-");
        
        System.out.println("市外局番:"+tels[0]);
        System.out.println("市内局番:"+tels[1]);
        System.out.println("加入者番号:"+tels[2]);
    }
}