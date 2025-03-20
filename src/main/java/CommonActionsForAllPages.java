import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;

/**
 * общие методы для всех страниц
 */

public class CommonActionsForAllPages {
    private WebDriver driver;
    private DataProperties dataProperties;
    private CommonActionsForAllPages commonActionsForAllPages;
    private Header header;
    private ModalLogIn modalLogIn;
    private WebDriverWait wait30;

    public CommonActionsForAllPages(WebDriver driver) {
        this.driver = driver;
    }

    public void startBrowser() {
        commonActionsForAllPages = new CommonActionsForAllPages(driver);
        dataProperties = new DataProperties();

        commonActionsForAllPages.getSizeWindowForAllTests();
        commonActionsForAllPages.getImplicitlyWaitForAllTests();

        String rootUrl = dataProperties.getDataProperties("config.properties" ,"stand");
        driver.get(rootUrl); // переходим на страницу
        System.out.println("Стенд = " + rootUrl);
    }

    public void auth() {
        startBrowser();
        header = new Header();
        modalLogIn = new ModalLogIn();
        wait30 = (new WebDriverWait(driver, Duration.ofSeconds(30)));

        wait30.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(header.getLinkLogIn())));
        driver.findElement(By.xpath(header.getLinkLogIn())).click();
        wait30.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(modalLogIn.getInputUsername())));
        driver.findElement(By.xpath(modalLogIn.getInputUsername())).sendKeys(dataProperties.getDataProperties("config.properties","login"));
        driver.findElement(By.xpath(modalLogIn.getInputPassword())).sendKeys(dataProperties.getDataProperties("config.properties","password"));
        driver.findElement(By.xpath(modalLogIn.getButtonLogIn())).click();
        wait30.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(header.getBlockNameOfUser())));
    }

    // получить размер окна для всех тестов
    public void getSizeWindowForAllTests() {
        driver.manage().window().maximize();
    }

    // получить неявное ожидание при каждом поиске для всех тестов
    public void getImplicitlyWaitForAllTests() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    // переходим на указанную вкладку
    public void goNextTab(int numberTab) {
        ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
        driver.switchTo().window(tabs2.get(numberTab));
    }

    // закрываем указанную вкладку
    public void closeTab() {
        driver.close();
    }

    // клик по невидимому элементу
    public void clickOnInvisibleElement(WebElement element) throws InterruptedException {
        String script = "var object = arguments[0];"
                + "var theEvent = document.createEvent(\"MouseEvent\");"
                + "theEvent.initMouseEvent(\"click\", true, true, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);"
                + "object.dispatchEvent(theEvent);"
                ;
        ((JavascriptExecutor)driver).executeScript(script, element);
        Thread.sleep(1000);
    }

    // наведение на элемент
    public void mouseMoveOnElement(WebElement element) throws InterruptedException {
        String script = "var object = arguments[0];"
                + "var theEvent = document.createEvent(\"MouseEvent\");"
                + "theEvent.initMouseEvent(\"mousemove\", true, true, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);"
                + "object.dispatchEvent(theEvent);"
                ;
        ((JavascriptExecutor)driver).executeScript(script, element);
        Thread.sleep(1000);
    }
}
