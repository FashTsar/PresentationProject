import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Objects;

import static io.qameta.allure.Allure.step;

public class CatalogTests {
    private DataProperties dataProperties;
    private WebDriver driver;
    private WebDriverWait wait30;
    private CommonActionsForAllPages commonActionsForAllPages;
    private ActionsForReportAfterStep actionsForReportAfterStep;
    private PageCatalog pageCatalog;
    private PageProductCard pageProductCard;

    @BeforeEach
    public void setUp() {
        dataProperties = new DataProperties();
        System.setProperty("webdriver.chrome.driver", dataProperties.getDataProperties("config.properties","driver"));
        driver = new ChromeDriver(); // инициализация браузера Chrome

        wait30 = (new WebDriverWait(driver, Duration.ofSeconds(30)));
        commonActionsForAllPages = new CommonActionsForAllPages(driver);
        actionsForReportAfterStep = new ActionsForReportAfterStep(driver);
        pageCatalog = new PageCatalog();
        pageProductCard = new PageProductCard();

        commonActionsForAllPages.auth();
    }

    @AfterEach
    public void tearDown() {
        System.out.println("URL = " + driver.getCurrentUrl());
        driver.quit();
        System.gc(); // чистим память
    }

    @Epic("Каталог")
    @Feature("Проверка перехода в карточку товара")
    @Test
    public void checkGoProductCard() {
        boolean result = false;

        // удаляем старые скриншоты
        actionsForReportAfterStep.deleteScreenshot("checkGoProductCard");

        // 1. собираем название товара, цену и описание и кликаем на название товара
        step("1. собираем название товара, цену и описание и кликаем на название товара");
        String productImg = driver.findElement(By.xpath(pageCatalog.getProductImg(1))).getDomAttribute("src");
        String productTitle = driver.findElement(By.xpath(pageCatalog.getProductTitle(1))).getText();
        String productPrice = driver.findElement(By.xpath(pageCatalog.getProductPrice(1))).getText();
        String productDescription = driver.findElement(By.xpath(pageCatalog.getProductDescription(1))).getText();
        driver.findElement(By.xpath(pageCatalog.getProductTitle(1))).click();
        wait30.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(pageProductCard.getButtonAddToCart())));
        actionsForReportAfterStep.reportAfterStep("checkGoProductCard", "1");

        // 2. проверяем что данные на странице совпадают с карточкой
        step("2. проверяем что данные на странице совпадают с карточкой");
        String productImgCard = driver.findElement(By.xpath(pageProductCard.getProductImg())).getDomAttribute("src");
        String productTitleCard = driver.findElement(By.xpath(pageProductCard.getProductTitle())).getText();
        String productPriceCard = driver.findElement(By.xpath(pageProductCard.getProductPrice())).getText();
        String productDescriptionCard = driver.findElement(By.xpath(pageProductCard.getProductDescription())).getText();

        if(Objects.equals(productImgCard, productImg)
                && Objects.equals(productTitleCard, productTitle)
                && productPriceCard.contains(productPrice)
                && Objects.equals(productDescriptionCard, productDescription)) result = true;
        actionsForReportAfterStep.reportAfterStep("checkGoProductCard", "2", result);

        Assertions.assertTrue(result, "Тест провалился");
    }
}
