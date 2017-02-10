package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/**
 * Created by Tetiana on 05.02.2017.
 */
class CommonPage {

     WebDriver driver;
     String pageName = "";
     int timeout = 10;

    CommonPage(WebDriver driver, String pageName) {
        this.pageName = pageName;
        this.driver = driver;
        waiteUntilPageLoaded();
        verifyPageName();
        PageFactory.initElements(driver, this);
    }


    private void verifyPageName() {
        String currentTitle = driver.getTitle();
        System.out.println("Open page: " + currentTitle);
    }

     void waiteUntilPageLoaded() {
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
    }

     void clickOn(By locator)
    {
        final WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.refreshed(
                ExpectedConditions.elementToBeClickable(locator)));
        driver.findElement(locator).click();
    }
}
