import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Header {
    /*
    ---------- ЭЛЕМЕНТЫ НА СТРАНИЦЕ ----------
     */

    public String getBlockHeader() {
        return "//div[@id='navbarExample']";
    }

    public String getLinkLogIn() {
        return getBlockHeader()+"//a[@id='login2']";
    }

    public String getBlockNameOfUser() {
        return getBlockHeader()+"//a[@id='nameofuser']";
    }

    public String getCart() {
        return getBlockHeader()+"//a[@id='cartur']";
    }
}
