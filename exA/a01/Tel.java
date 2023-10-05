public class Tel {
    public static void main(String[] args) {
        String[] telNum = args[0].split("-");
        System.out.println("市外局番：" + telNum[0]);
        System.out.println("市内局番：" + telNum[1]);
        System.out.println("加入者番号：" + telNum[2]);
    }
}