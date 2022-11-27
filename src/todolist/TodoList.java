package todolist;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

/**
 // Этот класс представляет ToDoList, который содержит ArrayList объектов Task
 **/

public class TodoList {
    // Массив список объектов задачи
    private ArrayList<Task> taskList;

    /**
     // создание объекта ToDoList
     */
    public TodoList() {
        taskList = new ArrayList<>();
    }

    /**
     // Добавление объекта задачи в ArrayList
     // title Строка, содержащая заголовок задачи, и она не может быть пустой или null.
     //  project Строка, содержащая имя проекта, связанного с задачей, и это может быть пустая строка.
     // параметр указывает дату выполнения задачи в формате гггг-мм-дд
     */
    public void addTask(String title, String project, LocalDate dueDate) {
        this.taskList.add(new Task(title,project,dueDate));
    }

    /**
     / Способ считывания значения от пользователя (стандартный ввод, т.е. терминал)
     // для создания объекта Task и добавления в ArrayList задач
     // возвращает значение true, если объект Tasks создан и добавлен в ArrayList, в противном случае значение false
     */
    public boolean readTaskFromUser() {
        Scanner scan = new Scanner(System.in);

        try {
            System.out.println(Messages.GREEN_TEXT + "Пожалуйста, введите следующие данные, чтобы добавить задачу:" + Messages.RESET_TEXT);
            System.out.print(">>> Название задачи  : ");
            String title = scan.nextLine();
            System.out.print(">>> Название проекта: ");
            String project = scan.nextLine();
            System.out.print(">>> Due Date [example: 2022-12-27] : ");
            LocalDate dueDate = LocalDate.parse(scan.nextLine());

            this.taskList.add(new Task(title,project,dueDate));
            Messages.showMessage("Задача успешно добавлена", false);

            return true;
        } catch (Exception e) {
            Messages.showMessage(e.getMessage(),true);
            return false;
        }

    }

    /**
     // Способ считывания значения от пользователя (стандартный ввод, т.е. терминал)
     //* и обновите данный объект Task в ArrayList задач
      // task объект задачи, значение которого необходимо обновить с помощью пользовательского ввода
     // возвращает значение true, если объект Tasks обновлен в ArrayList, в противном случае значение false
     */
    public boolean readTaskFromUserToUpdate(Task task) {
        Scanner scan = new Scanner(System.in);
        boolean isTaskUpdated = false;

        try {
            System.out.println(Messages.GREEN_TEXT + "Пожалуйста, введите следующие данные, чтобы обновить задачу:"
                    + "\nЕсли вы не хотите изменять какое-либо поле, просто нажмите клавишу ENTER!" + Messages.RESET_TEXT);
            System.out.print(">>> Название задачи  : ");
            String title = scan.nextLine();
            if (!(title.trim().equals("") || title == null)) {
                task.setTitle(title);
                isTaskUpdated = true;
            }

            System.out.print(">>> Название проекта: ");
            String project = scan.nextLine();
            if (!(project.trim().equals("") || project == null)) {
                task.setProject(project);
                isTaskUpdated = true;
            }

            System.out.print(">>> Due Date [example: 2022-12-27] : ");
            String dueDate = scan.nextLine();
            if (!(dueDate.trim().equals("") || dueDate == null)) {
                task.setDueDate(LocalDate.parse(dueDate));
                isTaskUpdated = true;
            }

            Messages.showMessage("Задача заключается в " + (isTaskUpdated ? "обновлено успешно" : "НЕ измененный") + ": Возврат в главное меню", false);

            return true;
        } catch (Exception e) {
            Messages.showMessage(e.getMessage(), true);
            return false;
        }
    }

    /**
     // Способ отображения содержимого ArrayList с первым столбцом в качестве номера задачи
     */
    public void listAllTasksWithIndex() {
        String displayFormat = "%-4s%-35s %-20s %-10s %-10s";

        if (taskList.size()>0) {
            System.out.println(String.format(displayFormat,"NUM","TITLE","PROJECT","DUE DATE","COMPLETED"));
            System.out.println(String.format(displayFormat,"===","=====","=======","========","========="));
        } else {
            System.out.println(Messages.RED_TEXT + "Никаких задач для показа" + Messages.RESET_TEXT);
        }

        taskList.stream()
                .forEach(task -> System.out.println(String.format(displayFormat,
                        taskList.indexOf(task)+1,
                        task.getTitle(),
                        task.getProject(),
                        task.getDueDate(),
                        (task.isComplete()?"Да":"Нет")
                )));
    }

    /**
     //Метод для отображения содержимого ArrayList
     //  Сортирует строку, содержащую число "2", для сортировки по проекту, в противном случае она будет сортироваться по дате
     */
    public void listAllTasks(String sortBy) {
        Messages.separator('=',75);
        System.out.println(
                "Общее количество задач = " + taskList.size() +
                        "\t\t (Выполнено = " + completedCount() + "\t\t" +
                        Messages.RED_TEXT + " Не Выполнено = " + notCompletedCount() + Messages.RESET_TEXT +
                        " )");
        Messages.separator('=',75);

        if (sortBy.equals("2")) {
            String displayFormat = "%-20s %-35s %-10s %-10s";

            if (taskList.size()>0) {
                System.out.println(String.format(displayFormat,"PROJECT","TITLE","DUE DATE","COMPLETED"));
                System.out.println(String.format(displayFormat,"=======","=====","========","========="));
            } else {
                System.out.println(Messages.RED_TEXT + "No tasks to show" + Messages.RESET_TEXT);
            }

            taskList.stream()
                    .sorted(Comparator.comparing(Task::getProject))
                    .forEach(task -> System.out.println(String.format(displayFormat,task.getProject(),
                            task.getTitle(),
                            task.getDueDate(),
                            (task.isComplete()?"Да":"Нет")
                    )));
        } else {
            String displayFormat = "%-10s %-35s %-20s %-10s";

            if (taskList.size() > 0) {
                System.out.println(String.format(displayFormat,"DUE DATE","TITLE","PROJECT" , "COMPLETED"));
                System.out.println(String.format(displayFormat,"========","=====","=======" , "========="));
            } else {
                System.out.println(Messages.RED_TEXT + "Никаких задач для показа" + Messages.RESET_TEXT);
            }

            taskList.stream()
                    .sorted(Comparator.comparing(Task::getDueDate))
                    .forEach(task -> System.out.println(String.format(displayFormat,task.getDueDate(),
                            task.getTitle(),
                            task.getProject(),
                            (task.isComplete() ? "Да" : "Нет")
                    )));
        }
    }

    /**
     // Способ выбора конкретного объекта задачи из списка массивов и выполнения операций редактирования
     //  параметр selectedTask Номер задачи, выбранный пользователем из заданного списка для выполнения операций редактирования
     // выдает исключение NullPointerException, если номер задачи задан как пустая строка или null
     // вызывает исключение ArrayIndexOutOfBoundsException, если номер задачи не попадает в диапазон индексов ArrayList
     */
    public void editTask(String selectedTask) throws NullPointerException {
        try {
            // checking if the task number is given and empty string or null
            if (selectedTask.trim().equals("") || selectedTask == null) {
                throw new NullPointerException("ПУСТОЙ/НУЛЕВОЙ НОМЕР ЗАДАЧИ: Возврат в главное меню");
            }

            int taskIndex = Integer.parseInt(selectedTask) - 1;
            if (taskIndex < 0 || taskIndex > taskList.size()) {
                throw new ArrayIndexOutOfBoundsException("НОМЕР ЗАДАЧИ НЕ УКАЗАН В СПИСКЕ ЗАДАЧ: Возврат в главное меню");
            }

            Task task = taskList.get(taskIndex);

            Messages.showMessage("Номер задачи " + selectedTask + "  выбирается:" + task.formattedStringOfTask(), false);

            Messages.editTaskMenu();
            Scanner scan = new Scanner(System.in);
            String editChoice = scan.nextLine();
            switch (editChoice) {
                case "1":
                    readTaskFromUserToUpdate(task);
                    break;
                case "2":
                    task.markCompleted();
                    Messages.showMessage("Номер задачи" + selectedTask + " помечено как завершенное: Возврат к Main Menu", false);
                    break;
                case "3":
                    taskList.remove(task);
                    Messages.showMessage("Номер задачи " + selectedTask + " удалено: Возвращение к Main Menu", true);
                    break;
                default:
                    Messages.showMessage("Возвращаясь к Main Menu", true);
            }
        } catch (Exception e) {
            Messages.showMessage(e.getMessage(),true);
        }
    }

    /**
     // Метод подсчета количества задач со статусом выполненных
     // возвращает количество задач со статусом выполненных
     */
    public int completedCount() {
        return (int) taskList.stream()
                .filter(Task::isComplete)
                .count();
    }

    /**
     // Метод подсчета количества задач со статусом незавершенных
     // возвращает количество задач со статусом незавершенных
     */
    public int notCompletedCount() {
        return (int) taskList.stream()
                .filter(task -> !task.isComplete())
                .count();
    }

    /**
     // Этот метод считает файл данных с диска, который будет содержать данные ранее сохраненных задач.
     // filename строка, указывающая полный путь и расширение файла данных, например, "ресурсы/задачи.obj"
    // возвращает значение true, если операция чтения прошла успешно, в противном случае значение false
     */
    public boolean readFromFile(String filename) {
        boolean status = false;

        try {
            if (!Files.isReadable(Paths.get(filename))) {
                Messages.showMessage("Файл данных, т.е., " + filename + " не существует", true);
                return false;
            }

            FileInputStream fileInputStream = new FileInputStream(filename);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            taskList = (ArrayList<Task>) objectInputStream.readObject();

            objectInputStream.close();
            fileInputStream.close();
            return true;

        } catch (Exception e) {
            Messages.showMessage(e.getMessage(),true);
            return false;
        }
    }

    /**
     // Этот метод запишет данные задач из ArrayList в файл данных на диске, т.е. tasks.obj
     // filename строка, указывающая полный путь и расширение файла данных, например, "ресурсы/задачи.obj"
     // возвращает значение true, если операция чтения прошла успешно, в противном случае значение false
     */
    public boolean saveToFile(String filename) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(filename);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(taskList);

            objectOutputStream.close();
            fileOutputStream.close();
            return true;

        } catch (Exception e) {
            Messages.showMessage(e.getMessage(),true);
            return false;
        }
    }
}