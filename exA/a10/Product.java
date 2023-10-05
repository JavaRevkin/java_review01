import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.List;

public class Product {
    private int productCode;

    private String productName;

    private int productPrice;

    public List<String> getInfo() {
        return Arrays.asList(new DecimalFormat("000").format(productCode + 1), productName, NumberFormat.getNumberInstance().format(productPrice));
    }

    public Product(final int code, final String name, final int price) {
        productCode = code;
        productName = name;
        productPrice = price;
    }
}