import java.io.IOException;
import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) throws IOException {
        System.out.println("Добро пожаловать в Криптоанализатор!");
        StartMessage.helloMessage();
        boolean isRunning = true;
        while (isRunning) {
            Cipher cipher = new Cipher();
            Scanner scanner = new Scanner(System.in);
            int x = scanner.nextInt();
            switch (x) {
                case 1 ->
                        isRunning = cipher.encrypt();    //C:\JavaRushProject\com.javarush.kuleshov.cryptoanalyzer\test\hello.txt
                case 2 -> cipher.decrypt();
                case 3 -> System.out.println("Попытаемся через bruteforce");
                case 4 -> System.out.println("Что за зверь такой статистический анализ");
                case 0 -> {
                    System.out.println("Увидимся в следующий раз:)");
                    isRunning = false;
                }
                default -> {
                    System.out.println("Некорректный ввод, попробуйте повторить ввод:");
                }
            }
        }
    }
}