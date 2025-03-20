import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static io.qameta.allure.Allure.step;

public class AuthTests {
    private DataProperties dataProperties;
    private WebDriver driver;
    private ActionsForReportAfterStep actionsForReportAfterStep;
    private CommonActionsForAllPages commonActionsForAllPages;
    private Header header;

    @BeforeEach
    public void setUp() {
        dataProperties = new DataProperties();
        System.setProperty("webdriver.chrome.driver", dataProperties.getDataProperties("config.properties","driver"));
        driver = new ChromeDriver(); // инициализация браузера Chrome

        actionsForReportAfterStep = new ActionsForReportAfterStep(driver);
        commonActionsForAllPages = new CommonActionsForAllPages(driver);
        header = new Header();
    }

    @AfterEach
    public void tearDown() {
        System.out.println("URL = " + driver.getCurrentUrl());
        driver.quit();
        System.gc(); // чистим память
    }

    @Epic("Авторизация")
    @Feature("Проверка авторизации пользователя при указании корректных данных")
    @Test
    public void checkAuthPositive() {
        boolean result = false;

        // удаляем старые скриншоты
        actionsForReportAfterStep.deleteScreenshot("checkAuthPositive");

        // 1. авторизуемся
        step("1. авторизуемся");
        commonActionsForAllPages.auth();
        actionsForReportAfterStep.reportAfterStep("checkAuthPositive", "1");

        // 2. проверяем что авторизовались
        step("2. проверяем что авторизовались");
        String grabName = driver.findElement(By.xpath(header.getBlockNameOfUser())).getText();
        if (grabName.contains(dataProperties.getDataProperties("config.properties","login"))) result = true;
        actionsForReportAfterStep.reportAfterStep("checkAuthPositive", "2", result);

        Assertions.assertTrue(result, "Тест провалился");
    }
}
