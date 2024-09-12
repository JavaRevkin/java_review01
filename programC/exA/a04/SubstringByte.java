package a04;

import java.util.Scanner;

public class SubstringByte {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in,"shift_jis");
        
        System.out.print("備考文字列を入力してください: ");
        
        String input = scanner.nextLine();
        
        int intMaxBytes = 20;
        
        scanner.close();

        String truncatedString = truncateToBytes(input, intMaxBytes);
        
        System.out.println("切り出し後: " + truncatedString);
    }
    
    public static String truncateToBytes(String input, int intMaxBytes) {
        byte[] bytes = input.getBytes();
        if (bytes.length <= intMaxBytes) {
            return input;
        }
        
        int intByteCount = 0;
        StringBuilder result = new StringBuilder();
        for (char c : input.toCharArray()) {
            int intCharByteSize =  (c <= 0x7F) ? 1 : 2;
            
            if (intByteCount + intCharByteSize > intMaxBytes) {
                break;
            }
            
            result.append(c);
            intByteCount += intCharByteSize;
        }
        return result.toString();
    }
}
