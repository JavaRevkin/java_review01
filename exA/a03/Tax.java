import java.text.NumberFormat;

public class Tax {
    public static final int MIN_PRICE = 0;

    public static final int MAX_PRICE = 99999;

    public static void main(String[] args) {
        int price = Integer.parseInt(args[0]);
        float taxRate = Float.parseFloat(args[1]);
        if (price < MIN_PRICE || price > MAX_PRICE) {
            System.out.println("The price that can be entered is 0 to 99,999");
            return;
        }
        NumberFormat ni = NumberFormat.getNumberInstance();
        System.out.println("税率：" + ni.format(taxRate) + "％");
        int tax = (int)(taxRate / 100.0F * price);
        System.out.println("税込金額：" + ni.format(price + tax) + "円　消費税額：" + ni.format(tax) + "円");
        tax = (int)(taxRate / (taxRate + 100.0F) * price);
        System.out.println("税抜金額：" + ni.format(price - tax) + "円　消費税額：" + ni.format(tax) + "円");
    }
}