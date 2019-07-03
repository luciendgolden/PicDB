package at.technikum.swei.foundation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateUtil {

  public static LocalDate convertStringToLocalDate(String date){
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    //convert String to LocalDate
    LocalDate localDate = LocalDate.parse(date, formatter);

    return localDate;
  }

}
