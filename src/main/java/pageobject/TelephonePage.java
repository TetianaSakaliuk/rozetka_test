package pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebElement;


/**
 * Created by Tetiana on 05.02.2017.
 */
public class TelephonePage extends CommonPage {

    @FindBy(xpath = "// a[contains (text(), 'Смартфоны')]")
    private WebElement smarphones;

    public TelephonePage(WebDriver driver, String pageName) {
        super(driver, pageName);
    }

    public void clickMenu() {smarphones.click();}


}
