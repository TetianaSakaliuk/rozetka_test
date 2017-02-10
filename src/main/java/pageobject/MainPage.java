package pageobject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;


/**
 * Created by Tetiana on 05.02.2017.
 */
public class MainPage extends CommonPage {

    @FindBy(xpath = "//*[contains(@data-title, 'Телефоны, ТВ и электроника')]")
    private WebElement telephoneTVEl;

    public MainPage(WebDriver driver, String pageName) {
        super(driver, pageName);
    }
    public void clickMenu() {
        telephoneTVEl.click();
    }
}
