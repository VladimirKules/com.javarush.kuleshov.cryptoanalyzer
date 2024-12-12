import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.InputMismatchException;

public class Validator {

    public static boolean isFileExists(String filePath) {
        while (true) {
            try {
                Path path = Path.of(filePath);
                if (Files.isRegularFile(path)) {
                    return Files.isRegularFile(path);
                } else {
                    System.out.println("Вы указали путь к каталогу");
                }
            } catch (InvalidPathException e) {
                System.out.println("Некорректный путь");
            }
        }
    }

    public static boolean isDirectoryExists(String filePath) {
        Path path = Path.of(filePath);
        if (Files.isDirectory(path.getRoot())) {
            return true;
        }
        return false;
    }
}
