package todolist;

import java.io.Serializable;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Task implements Serializable {
    // Строка, содержащая заголовок задачи, и она не может быть пустой или null.
    private String title;
    // Строка, содержащая название проекта, связанного с задачей, и это может быть пустая строка.
    private String project;
    // Логическое значение, если true: задача выполнена, в противном случае false.
    private String taskDescription;
    // Строка, содержащая описание задачи, и она не может быть пустой или null.
    private boolean complete;
    // Дата выполнения задания в формате гггг-мм-дд
    private LocalDate dueDate;


    /**
     // Создание объекта класса Task
    // title Строка, содержащая заголовок задачи, и она не может быть пустой или null.
     // project Строка, содержащая имя проекта, связанного с задачей, и это может быть пустая строка.
    //параметр указывает дату выполнения задачи в формате гггг-мм-дд
     */
    public Task(String title, String project,String taskDescription, LocalDate dueDate) {

        this.setTitle(title);
        this.setProject(project);
        this.setTaskDescription(taskDescription);
        this.complete = false;
        this.setDueDate(dueDate);
    }
    /**
     // Способ получения описания задачи
     //возвращает строку, содержащую название задачи
     */
    public String getTaskDescription() {
        return taskDescription;
    }
    /**
     // Способ задать описания  задачи
     //title Строка, содержащая заголовок задачи, и она  может быть пустой или null.
     // выдает исключение NullPointerException, если заголовок равен нулю или пустой строке
     */
    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    /**
    // Способ получения названия задачи
     //возвращает строку, содержащую название задачи
     */
    public String getTitle() {
        return this.title;
    }

    /**
     // Способ задать заголовок объекта задачи
    //title Строка, содержащая заголовок задачи, и она не может быть пустой или null.
     // выдает исключение NullPointerException, если заголовок равен нулю или пустой строке
     */
    public void setTitle(String title) throws NullPointerException {
        if (title.trim().equals("") || title == null) {
            throw new NullPointerException("ОБЯЗАТЕЛЬНО: Заголовок не может быть пустым.");
        }
        this.title = title.trim();
    }

    /**
     //Способ получения названия проекта
     //возвращает строку, содержащую название проекта
     */
    public String getProject() {
        return this.project;
    }

    /**
     // Способ задать название проекта
     // Возвращяет project Строка, содержащая имя проекта, связанного с задачей, и это может быть пустая строка.
     */
    public void setProject(String project) {
        this.project = project.trim();
    }

    /**
     // Способ получения статуса завершения задачи
     // Возврощяет true: если задача помечена как выполненная, в противном случае она вернет значение false
     */
    public boolean isComplete() {
        return this.complete;
    }

    /**
    // Способ пометить задачу как выполненную
    // возвращает обновленное значение поля complete
     */
    public boolean markInComplete() {
        this.complete = false;
        return this.complete;
    }

    /**
     // Способ пометить задачу как выполненную
   // возвращает обновленное значение поля complete
     */
    public boolean markCompleted() {
        this.complete = true;
        return this.complete;
    }

    /**
     // Метод для получения даты выполнения задачи
     // возвращает дату выполнения задачи в качестве объекта LocalDate
     */
    public LocalDate getDueDate() {
        return dueDate;
    }

    /**
    // Способ установки даты выполнения задачи
     // Определяет дату выполнения задачи в формате гггг-мм-дд
     // выдает исключение DateTimeException, если заданная дата является прошедшей датой
     */
    public void setDueDate(LocalDate dueDate) throws DateTimeException {
        // Выдает исключение DateTimeException, если задана прошедшая дата
        if (dueDate.compareTo(LocalDate.now())<0) {
            throw new DateTimeException("Прошедшая дата не допускается");
        }

        //Убедитесь, что срок оплаты сохранен в формате гггг-ММ-дд
        DateTimeFormatter formattedDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.dueDate = LocalDate.parse(dueDate.format(formattedDate));
    }

    /**
    // Способ получения данных задачи в виде форматированной строки для отображения в нескольких строках
     // возвращает форматированную строку всех полей задачи
     */
    public String formattedStringOfTask() {
        return (
                "\nЗадача     : " + title +
                        "\nПроект   : " + project +
                        "\nОписание : " + taskDescription +
                        "\nСтатус   : " + (complete?"Завершено":"НЕ ЗАВЕРШЕНО") +
                        "\nДата  : " + dueDate +
                        "\n");
    }
}