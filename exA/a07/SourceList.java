import java.io.File;
import java.nio.file.Paths;

public class SourceList {
    public static void main(String[] args) {
        System.out.println("Javaソースファイル名");
        String exAPath = Paths.get("../").toAbsolutePath().normalize().toString();
        for (File dir : new File(exAPath.toString()).listFiles()) {
            File[] files = new File(dir.toString()).listFiles();
            if (files != null) for (File file : files) {
                String filePath = file.toString();
                if (filePath.substring(filePath.lastIndexOf(".")).equals(".java")) System.out.println("・" + filePath);
            }
        }
    }
}