package a07;
import java.io.File;

public class SourceList {
    
    public static void main(String[] args) {
        
        // パッケージ「exA」のディレクトリパスを指定
        String packagePath = "C:\\Users\\work\\Desktop\\課題\\応用A課題\\Sample\\src\\exA";
        File directory = new File(packagePath);

        // ディレクトリが存在するか確認
        if (directory.exists() && directory.isDirectory()) {
            // Javaソースファイルの一覧を表示
            listJavaFiles(directory);
        } else {
            System.out.println("指定されたディレクトリが存在しません。");
        }
    }

    // ディレクトリ内のJavaファイルをリストアップ
    public static void listJavaFiles(File directory) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    // サブディレクトリ内のファイルをリストアップ
                    listJavaFiles(file);
                } else if (file.getName().endsWith(".java")) {
                    // Javaファイルの絶対パスを表示
                    System.out.println(file.getAbsolutePath());
                }
            }
        }
    }
}