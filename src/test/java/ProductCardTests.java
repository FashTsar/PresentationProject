import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Objects;

import static io.qameta.allure.Allure.step;

public class ProductCardTests {
    private DataProperties dataProperties;
    private WebDriver driver;
    private WebDriverWait wait30;
    private CommonActionsForAllPages commonActionsForAllPages;
    private ActionsForReportAfterStep actionsForReportAfterStep;
    private PageCatalog pageCatalog;
    private PageProductCard pageProductCard;
    private Header header;
    private PageCart pageCart;
    private ApiCart apiCart;

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
        header = new Header();
        pageCart = new PageCart();
        apiCart = new ApiCart();

        apiCart.clearCart();
    }

    @AfterEach
    public void tearDown() {
        System.out.println("URL = " + driver.getCurrentUrl());
        driver.quit();
        System.gc(); // чистим память
        apiCart.clearCart();
    }

    @Epic("Карточка товара")
    @Feature("Проверка добавление товара в корзину из карточки товара")
    @Test
    public void checkAddToCartProduct() {
        if (Objects.equals(dataProperties.getDataProperties("priorityRunningTests.properties" ,"priorityRunningTestForCart"), "WebDriver")) {
            commonActionsForAllPages.auth();

            // удаляем старые скриншоты
            actionsForReportAfterStep.deleteScreenshot("checkAddToCartProduct");

            // 1. переходим в карточку товара
            step("1. переходим в карточку товара");
            String rootUrl = dataProperties.getDataProperties("config.properties", "stand");
            String linkProductCard = driver.findElement(By.xpath(pageCatalog.getProductTitle(1))).getDomAttribute("href");
            driver.get(rootUrl+linkProductCard); // переходим на страницу
            wait30.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(pageProductCard.getButtonAddToCart())));
            String productImgCard = driver.findElement(By.xpath(pageProductCard.getProductImg())).getDomAttribute("src");
            String productTitleCard = driver.findElement(By.xpath(pageProductCard.getProductTitle())).getText();
            String productPriceCard = driver.findElement(By.xpath(pageProductCard.getProductPrice())).getText();
            actionsForReportAfterStep.reportAfterStep("checkAddToCartProduct", "1");

            // 2. нажимаем на кнопку "Add to cart"
            step("2. нажимаем на кнопку \"Add to cart\"");
            driver.findElement(By.xpath(pageProductCard.getButtonAddToCart())).click();
            actionsForReportAfterStep.reportAfterStep("checkAddToCartProduct", "2");

            // 3. Ожидаем появления alert'а и проверяем текст в нём
            step("3. Ожидаем появления alert'а и проверяем текст в нём");
            Alert alert = wait30.until(ExpectedConditions.alertIsPresent());
            String alertText = alert.getText();
            alert.accept();
            actionsForReportAfterStep.reportAfterStep("checkAddToCartProduct", "3");

            Assertions.assertEquals("Product added.", alertText);

            // 4. Проверяем что товар появился в корзине
            step("4. Проверяем что товар появился в корзине");
            String linkCart = driver.findElement(By.xpath(header.getCart())).getDomAttribute("href");
            driver.get(rootUrl+linkCart); // переходим на страницу
            wait30.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(pageCart.getButtonPlaceOrder())));
            String productImgCart = driver.findElement(By.xpath(pageCart.getProductImg(1))).getDomAttribute("src");
            String productTitleCart = driver.findElement(By.xpath(pageCart.getProductTitle(1))).getText();
            String productPriceCart = driver.findElement(By.xpath(pageCart.getProductPrice(1))).getText();
            actionsForReportAfterStep.reportAfterStep("checkAddToCartProduct", "4");

            Assertions.assertEquals(productImgCart, productImgCard);
            Assertions.assertEquals(productTitleCart, productTitleCard);
            Assertions.assertTrue(productPriceCard.contains(productPriceCart));

        } else {
            System.out.println("Запуск WebDriver отключен для данного раздела для этого теста, включите его в \"priorityRunningTests.properties\" или запустите АПИ-тест.");
        }
    }
}
