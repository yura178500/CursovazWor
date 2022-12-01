package todolist;

import java.time.LocalDate;
import java.time.Month;
import java.util.Objects;

public enum FrequencyTask  {

    ONETIME( LocalDate.of( 2022 , 12 , 1 ) ) ,//"однократная
    DAILY( LocalDate.of( 2022 , 12 , 1 ) ), //ежедневная
    WEEKLY( LocalDate.of( 2022 , 12 , 1 ) ), //еженедельная
    MONTHLY( LocalDate.of( 2022 , 12 , 1 ) ), //ежемесячная
    ANNUAL( LocalDate.of( 2022 , 12 , 1 ) ) ; //ежегодная


    private LocalDate localDate ;

    // Constructor
     FrequencyTask(LocalDate ld) {

        Objects.requireNonNull(ld ) ;
        this.localDate = ld ;
    }

    // Getter
    public LocalDate getLocalDate() {
        return this.localDate ;
    }

}


