import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * методы для отчётов после шагов в тестах
 */

public class ActionsForReportAfterStep {
    private WebDriver driver;
    public ActionsForReportAfterStep(WebDriver driver) {
        this.driver = driver;
    }

    // сделать скриншот
    public void takeScreenshot(String folderName, String fileName) {
        File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshot, new File("screenshotsTests/" + folderName + "/" + fileName + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // удаление всех скриншотов из папки скриншота
    public void deleteScreenshot(String folderName) {
        try {
            Files.createDirectories(Paths.get("screenshotsTests/" + folderName));
        } catch (IOException ignored) {}
        String path = "screenshotsTests/" + folderName;
        for (File myFile : new File(path).listFiles())
            if (myFile.isFile()) myFile.delete();
    }

    // отчёт после шага. Значения: номер задачи и шаг
    public void reportAfterStep(String taskNumber, String stepNumber) {
        String fileName = "step" + stepNumber;
        takeScreenshot(taskNumber, fileName);
        Date dateNow = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("E dd.MM.yyyy 'и время' HH:mm:ss");
        String linkScreenshot = "screenshotsTests/" + taskNumber + "/" + fileName + ".png";
        System.out.println(taskNumber + " = шаг " + stepNumber + " Скриншот:" + linkScreenshot + " Текущая дата " + formatForDateNow.format(dateNow));
    }

    // отчёт после шага. Значения: номер задачи, шаг, результат проверки
    public void reportAfterStep(String taskNumber, String stepNumber, boolean result) {
        String fileName = "step" + stepNumber;
        takeScreenshot(taskNumber, fileName);
        Date dateNow = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("E dd.MM.yyyy 'и время' HH:mm:ss");
        String linkScreenshot = "screenshotsTests/" + taskNumber + "/" + fileName + ".png";
        System.out.println(taskNumber + " = шаг " + stepNumber + " Скриншот:" + linkScreenshot + " Текущая дата " + formatForDateNow.format(dateNow) + " Result of checking = " + result);
    }

    // отчёт после шага. Значения: номер задачи и шаг
    public void reportAfterStepForApiTests(String taskNumber, String stepNumber) {
        Date dateNow = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("E dd.MM.yyyy 'и время' HH:mm:ss");
        System.out.println(taskNumber + " = шаг " + stepNumber + " Текущая дата " + formatForDateNow.format(dateNow));
    }

    // отчёт после шага. Значения: номер задачи, шаг, результат проверки
    public void reportAfterStepForApiTests(String taskNumber, String stepNumber, boolean result) {
        Date dateNow = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("E dd.MM.yyyy 'и время' HH:mm:ss");
        System.out.println(taskNumber + " = шаг " + stepNumber + " Текущая дата " + formatForDateNow.format(dateNow) + " Result of checking = " + result);
    }
}
