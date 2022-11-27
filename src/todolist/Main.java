package todolist;

import java.util.Scanner;


public class Main {

    public static String filename = "Задача";


    public static void main(String args[]) {

        TodoList todoList = new TodoList();


        String menuChoice = "-17";

        try {
            Scanner input = new Scanner(System.in);

            todoList.readFromFile(filename);

            Messages.showMessage("Добро пожаловать в Ежедневник", false);

            while (!menuChoice.equals("4")) {
                Messages.mainMenu(todoList.notCompletedCount(), todoList.completedCount());
                menuChoice = input.nextLine();

                switch (menuChoice) {
                    case "1":
                        Messages.listAllTasksMenu();
                        todoList.listAllTasks(input.nextLine());
                        break;
                    case "2":
                        todoList.readTaskFromUser();
                        break;
                    case "3":
                        todoList.listAllTasksWithIndex();
                        Messages.editTaskSelection();
                        todoList.editTask(input.nextLine());
                        break;
                    case "4":
                        break;

                    default:
                        Messages.unknownMessage();
                }
            }


            todoList.saveToFile(filename);
            Messages.byeMessage();

        } catch (Exception e) {
            Messages.showMessage("UNCAUGHT EXCEPTION THROWN", true);
            System.out.println("Попытка записать несохраненные данные всех задач в файл данных");
            todoList.saveToFile(filename);
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
    }
}