import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

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
        actionsForReportAfterStep.reportAfterStep("checkGoProductCard", "2");

        Assertions.assertEquals(productTitleCard, productTitle);
        Assertions.assertEquals(productDescriptionCard, productDescription);
        Assertions.assertEquals(productImgCard, productImg);
        Assertions.assertTrue(productPriceCard.contains(productPrice));
    }
}
