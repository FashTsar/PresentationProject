import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileWriter;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;

/**
 * действия, не связанные напрямую с тестами
 */

public class OtherActions {
    private WebDriver driver;
    public static DataProperties dataProperties;

    public OtherActions(WebDriver driver) {
        this.driver = driver;
    }

    // генерация случайной почты
    public String getRandomEmailRtRu() {
        int comment = (int) Instant.now().getEpochSecond();
        return comment+".test@mail.ru";
    }

    // получить текущий день недели
    public int getDayOfWeek() {
        LocalDate today = LocalDate.now();
        DayOfWeek dayOfWeek = today.getDayOfWeek();
        return dayOfWeek.getValue();
    }

    // получить текущую дату
    public int getNowDateDay() {
        //Getting the current date value of the system
        LocalDate current_date = LocalDate.now();

        //getting the current year from the current_date
        return current_date.getDayOfMonth();
    }

    // получить текущий месяц
    public int getNowDateMount() {
        //Getting the current date value of the system
        LocalDate current_date = LocalDate.now();

        //getting the current year from the current_date
        return current_date.getMonthValue();
    }

    // получить текущий год
    public int getNowDateYear() {
        //Getting the current date value of the system
        LocalDate current_date = LocalDate.now();

        //getting the current year from the current_date
        return current_date.getYear();
    }

    // сделать в строке все буквы прописными, а первую букву заглавной
    String properCase (String inputVal) {
        // Empty strings should be returned as-is.
        if (inputVal.length() == 0) return "";

        // Strings with only one character uppercased.
        if (inputVal.length() == 1) return inputVal.toUpperCase();

        // Otherwise uppercase first letter, lowercase the rest.
        return inputVal.substring(0,1).toUpperCase()
                + inputVal.substring(1).toLowerCase();
    }

    // получаем название месяца
    public String getNameMount(String valueFilter) {
        String name = "";
        switch (valueFilter) {
            case  ("янв."):
                name = "Январь";
                break;
            case  ("фев."):
                name = "Февраль";
                break;
            case  ("март"):
                name = "Март";
                break;
            case  ("апр."):
                name = "Апрель";
                break;
            case  ("май"):
                name = "Май";
                break;
            case  ("июнь"):
                name = "Июнь";
                break;
            case  ("июль"):
                name = "Июль";
                break;
            case  ("авг."):
                name = "Август";
                break;
            case  ("сент."):
                name = "Сентябрь";
                break;
            case  ("окт."):
                name = "Октябрь";
                break;
            case  ("нояб."):
                name = "Ноябрь";
                break;
            case  ("дек."):
                name = "Декабрь";
                break;
        }
        return name;
    }

    // получаем номер месяца
    public String getNumberMount(String valueFilter) {
        String number = "";
        switch (valueFilter) {
            case  ("янв."):
                number = "01";
                break;
            case  ("фев."):
                number = "02";
                break;
            case  ("март"):
                number = "03";
                break;
            case  ("апр."):
                number = "04";
                break;
            case  ("май"):
                number = "05";
                break;
            case  ("июнь"):
                number = "06";
                break;
            case  ("июль"):
                number = "07";
                break;
            case  ("авг."):
                number = "08";
                break;
            case  ("сент."):
                number = "09";
                break;
            case  ("окт."):
                number = "10";
                break;
            case  ("нояб."):
                number = "11";
                break;
            case  ("дек."):
                number = "12";
                break;
        }
        return number;
    }

    public void saveTextInFile (String nameFile, String text) {
        try {
            FileWriter writer = new FileWriter(".idea/filesTextForTest/"+nameFile+".txt", true);

            writer.write(text);
            writer.write("\n");

            writer.close();

        } catch (IOException e) {
            System.out.println("Возникла ошибка во время записи, проверьте данные.");
        }
    }
}
