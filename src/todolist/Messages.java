package todolist;


public class Messages {

    //постоянное поле для хранения кода для сброса цвета шрифта текста
    public static final String RESET_TEXT = "\u001B[0m";
    //постоянное поле для хранения кода для изменения цвета шрифта текста на красный
    public static final String RED_TEXT = "\u001B[31m";
    //постоянное поле для хранения кода для изменения цвета шрифта текста на зеленый
    public static final String GREEN_TEXT = "\u001B[32m";

    /**
     * // Этот метод отобразит главное меню (меню верхнего уровня) на стандартном выходе (терминал)
     * // для отображения всех опций для выбора пользователем.
     * // @* @param incompleteTaskCount принимает количество незавершенных задач (int) для отображения в главном меню
     * // @param completedTaskCount принимает количество выполненных задач (int) для отображения в главном меню
     */
    public static void mainMenu(int incompleteTaskCount, int completedTaskCount) {
        System.out.println("\nМеню");
        System.out.println("===========\n");
        System.out.println("У тебя есть " + Messages.RED_TEXT
                + incompleteTaskCount + " Задача "
                + Messages.RESET_TEXT + " и " + Messages.GREEN_TEXT
                + completedTaskCount + " выполненная задача\n" + Messages.RESET_TEXT);
        System.out.println("Выберите вариант:");
        System.out.println("(1) Показать список задач (по дате или проекту)");
        System.out.println("(2) Добавить новую задачу");
        System.out.println("(3) Редактировать задачу (обновить, отметить как выполненную, удалить)");
        System.out.println("(4) Сохранить и выйти\n");
        System.out.println("(5) Время периодичности задачи ");
        System.out.print("Пожалуйста, укажите свой выбор [1-5]: ");
    }
    /**
     * // Этот метод выводит меню на стандартный вывод (терминал), чтобы показать параметры для отображения всех задач
     * //для выбора пользователем
     */
    public static void listAllTasksMenu() {
        System.out.println("\nОтображать все задачи");
        System.out.println("===================\n");
        System.out.println("Выберите вариант:");
        System.out.println("(1) Показать список задач по дате" +
                Messages.RED_TEXT + " [выбор по умолчанию, просто нажмите клавишу ENTER]" + Messages.RESET_TEXT);
        System.out.println("(2)Показать список задач по проектам");
        System.out.print("\nПожалуйста, укажите свой выбор [1-2]: ");
    }

    /**
     * // Этот метод отобразит пользователю приглашение ввести номер задачи для редактирования
     */
    public static void editTaskSelection() {
        System.out.println(GREEN_TEXT);
        System.out.print(">>> Введите номер задачи для редактирования и нажмите клавишу ENTER: ");
        System.out.print(RESET_TEXT);
    }

    /**
     * // Этот метод отобразит параметры меню редактирования на стандартном выводе (терминал)
     * // для выбора пользователем
     */
    public static void editTaskMenu() {
        System.out.println("\nПараметры редактирования задачи");
        System.out.println("======================\n");
        System.out.println("Выберите вариант:");
        System.out.println("(1) Изменить выбранную задачу");
        System.out.println("(2) Отметить выбранную задачу как ВЫПОЛНЕННУЮ");
        System.out.println("(3) Удалить выбранную задачу");
        System.out.println("(4) Вернуться в главное меню "
                + Messages.RED_TEXT + " [выбор по умолчанию, просто нажмите ENTER]" + Messages.RESET_TEXT);
        System.out.print("\nПожалуйста, укажите свой выбор [1-4]: ");
    }

    /**
     * // Этот метод отобразит сообщение ДОСВИДАНИЯ при завершении программы
     */
    public static void byeMessage() {
        System.out.println(GREEN_TEXT);
        System.out.println(">>> Все задачи сохраняются в файле данных");
        System.out.println(">>> ДОСВИДАНИЯ");
        System.out.println(RESET_TEXT);
    }

    /**
     * // Этот метод отобразит сообщение об ошибке, если пользователь введет параметр, который не
     * // из вариантов, указанных в главном меню
     */
    public static void unknownMessage() {
        System.out.println(RED_TEXT);
        System.out.println(">>> Неправильный выбор: Пожалуйста, введите номер из предложенных вариантов ");
        System.out.print(RESET_TEXT);
    }

    /**
     * // Это сообщение отобразит любое заданное сообщение красным или зеленым текстом на стандартном выводе (терминал)
     * // @param message текстовое сообщение в виде строки
     * // @param warning логическое значение, true для вывода предупреждения красным текстом и false
     * // для печати сообщения зеленым текстом на стандартном выводе (терминал)
     */
    public static void showMessage(String message, boolean warning) {
        System.out.println(warning ? RED_TEXT : GREEN_TEXT);
        System.out.println(">>> " + message);
        System.out.println(RESET_TEXT);
    }

    /**
     * // Это сообщение выведет данный символ на стандартный вывод (терминал) заданное количество раз
     * // @param charToPrint символ, заключенный в одинарные кавычки для печати, т.е. '='
     * // @param умножает целое число на повторную печать заданного символа
     */
    public static void separator(char charToPrint, int times) {
        for (int index = 0; index < times; index++) System.out.print(charToPrint);
        System.out.println("");
    }

}