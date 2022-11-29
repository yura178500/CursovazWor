package todolist;

import java.time.LocalDate;
import java.util.Date;
import java.util.TimerTask;

public class ScheduledTask extends TimerTask {
    Date now;

    // Добавляем такс

    public void run() {
        now = new Date();
        System.out.println("Текущая дата и время : " + now);
    }
}

