import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Cipher {
    private static final char[] ALPHABET = {'а', 'б', 'в', 'г', 'д', 'е', 'ж', 'з',
            'и', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ',
            'ъ', 'ы', 'ь', 'э', 'ю', 'я', '.', ',', '«', '»', '"', '\'', ':', '!', '?', ' '};
    static final int MAX_KEY = ALPHABET.length - 1;
    private StringBuilder result = new StringBuilder();
    private Scanner scanner = new Scanner(System.in);
    private String sourceFile;
    private String destFile;
    private Integer key;

    private Integer getKey(String string) {
        while (true) {
            try {
                System.out.println(string);
                int input = scanner.nextInt();
                scanner.nextLine();
                if (input >= 0 && input <= MAX_KEY) {
                    return input;
                } else {
                    System.out.println("Неверный ключ. Введите число от 0 до " + MAX_KEY + ".");
                }
            } catch (InputMismatchException e) {
                System.out.println("Введите целое число");
                scanner.nextLine();
            }
        }
    }

    private String getFilePath(String string) throws IOException {
        while (true) {
            try {
                System.out.println(string);
                String filePath = scanner.nextLine();
                Path path = Path.of(filePath);
                long size =Files.size(path);
                if (Files.isRegularFile(path) &&size>0) {
                    return filePath;
                } else if (size==0) {
                    System.out.println(StartMessage.FILE_EMPTY);
                } else {
                    System.out.println(StartMessage.PATH_NOT_VALID);
                }
            } catch (IOException e) {
                System.out.println(StartMessage.PATH_NOT_VALID);
            }
        }
    }

    private String setFilePath(String string) {
        while (true) {
            try {
                System.out.println(string);
                String filePath = scanner.nextLine();
                Path path = Path.of(filePath);
                if (Files.isDirectory(path.getParent())) {
                    return filePath;
                } else {
                    System.out.println(StartMessage.PATH_NOT_EXIST);
                }
            } catch (NullPointerException e) {
                System.out.println(StartMessage.PATH_EXAMPLE);
            }
        }
    }

    public boolean encrypt() throws IOException {
        sourceFile = getFilePath("Введите путь к файлу, который хотите зашифровать:");
        List<String> text = FileManager.readFile(sourceFile);
        if (text == null) {
            return false;
        }
        key = getKey("Введите ключ для шифрования текстового файла:");
        destFile = setFilePath("Введите путь к файлу, в который хотите записать результат:");
        for (String s : text) {
            for (char c : s.toCharArray()) {
                if (alphabetNumber(c) > 0) {
                    int newIndex = (alphabetNumber(c) + key) % ALPHABET.length;
                    if (Character.isUpperCase(c)) {
                        result.append(Character.toUpperCase(ALPHABET[newIndex]));
                    } else {
                        result.append(ALPHABET[newIndex]);
                    }
                } else {
                    result.append(c);
                }
            }
            result.append("\n");
        }
        FileManager.writeFile(result.toString(), destFile);
        System.out.println("Файл успешно зашифрован. Находится по следующему пути: " + destFile);
        StartMessage.helloMessage();
        return true;
    }

    public int alphabetNumber(char c) {
        for (int i = 0; i < ALPHABET.length; i++) {
            if (Character.toLowerCase(c) == ALPHABET[i]) {
                return i;
            }
        }
        return -1;
    }

    public void decrypt() throws IOException {
        sourceFile = getFilePath("Введите путь к файлу, который хотите расшифровать:");
        List<String> text = FileManager.readFile(sourceFile);
        key = getKey("Введите ключ для дешифрования текстового файла:");
        destFile = setFilePath("Введите путь к файлу, в который хотите записать расшифрованный файл:");
        for (String s : text) {
            for (char c : s.toCharArray()) {
                if (alphabetNumber(c) > 0) {
                    int newIndex = (alphabetNumber(c) + ALPHABET.length - key) % ALPHABET.length;
                    if (Character.isUpperCase(c)) {
                        result.append(Character.toUpperCase(ALPHABET[newIndex]));
                    } else {
                        result.append(ALPHABET[newIndex]);
                    }
                } else {
                    result.append(c);
                }
            }
            result.append("\n");
        }
        FileManager.writeFile(result.toString(), destFile);
        System.out.println("Файл успешно зашифрован. Находится по следующему пути: " + destFile);
        StartMessage.helloMessage();
    }
}
