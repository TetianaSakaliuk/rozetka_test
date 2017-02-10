package pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


/**
 * Created by Tetiana on 05.02.2017.
 */
public class TelephoneAndElectronicDevicesPage extends CommonPage {

    @FindBy(xpath= "//h4/a[contains (text(), 'Телефоны')]")
    private WebElement telephones;

    public TelephoneAndElectronicDevicesPage(WebDriver driver, String pageName) {
        super(driver, pageName);
    }

    public void  clickMenu() {
        telephones.click();
    }
}
