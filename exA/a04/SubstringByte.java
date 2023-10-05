import java.io.UnsupportedEncodingException;

public class SubstringByte {
    public static final int MAX_BYTES = 20;
    
    public static void main(String[] args) {
        try {
            byte[] byteStr = args[0].getBytes("Shift-JIS");
            System.out.println(new String(byteStr, 0, Math.min(MAX_BYTES, byteStr.length), "Shift-JIS"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}