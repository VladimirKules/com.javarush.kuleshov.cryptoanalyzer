import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileManager {
    /*public String readFile(String filePath) {
        // Логика чтения файла
        return filePath;
    }*/
    public static List<String> readFile(String filePath) {
        try {
            Path path = Paths.get(filePath);
            if (Files.size(path) == 0) {
                System.out.println("Нет данных для шифрования в файле. Всего доброго!");

                return null;
            }
            return Files.readAllLines(path);
        }catch(NoSuchFileException e){
            System.out.println("Укажите корректный каталог");
            return null;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void writeFile(String content, String filePath) /*throws IOException*/ {
        try (FileWriter writer = new FileWriter(filePath)) {

            writer.write(content);

        }catch (IOException e) {
            System.err.println("Ошибка при записи в файл: " + e.getMessage());
            //throw e; // Перехватываем и выбрасываем исключение
        }
    }
}
