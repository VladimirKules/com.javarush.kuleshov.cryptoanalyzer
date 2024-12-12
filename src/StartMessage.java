public class StartMessage {
    static final String FILE_EMPTY = "Данный файл пустой!";
    static final String PATH_NOT_VALID = "Данный путь некорректен!";
    static final String PATH_NOT_EXIST = "Такого пути не существует!";
    static final String PATH_EXAMPLE = "Введите путь в формате ИмяДиска:\\Каталог\\ИмяФайла.Расширение";

    public static void helloMessage() {
        System.out.println("""
                
                Укажите номер пункта, который бы вы хотели сделать:
                1.Зашифровать текст
                2.Расшифровать текст
                3.Расшифровать с помощью brute force
                4.Расшифровать методом статистического анализа
                0.Выйти из программы""");
    }
}
