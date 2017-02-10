package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by Tetiana on 05.02.2017.
 */
public class SmartphonePage extends CommonPage {
    public SmartphonePage(WebDriver driver, String pageName) {
        super(driver, pageName);
    }

    private List<Map> getListOfTops(int maxAttempts) {
        List<Map> listOfTops = new ArrayList<Map>();
        int attempts = 0;
        while (attempts < maxAttempts) {
            try {
                List<WebElement> listOfEl = driver.findElements(By.xpath("//i[contains(@class, 'middle-popularity')]/../../../../.."));
                for (WebElement el : listOfEl) {
                    Map item = new HashMap<String, String>();
                    item.put("smartphone_name", el.findElement(By.xpath(".//div[contains(@class, 'tile-i-title')]/a")).getAttribute("outerText"));
                    item.put("smartphone_price", el.findElement(By.xpath(".//div[@class='g-price-uah']")).getAttribute("textContent") );
                    listOfTops.add(item);
                }
                break;
            } catch (StaleElementReferenceException e) {
                System.out.println("Caught StaleElementReferenceException: stale element reference. Retrying...");
            }
            attempts++;
        }
        if (attempts >= maxAttempts)
            throw new StaleElementReferenceException("Max amount of retries reached: " + maxAttempts + ". StaleElementReferenceException still occurs.");
        return listOfTops;
    }

    private void switchToResultPage(int pageNumber) {
        clickOn(By.xpath("//li[@id='page" + pageNumber + "']"));
        waiteUntilPageLoaded();
    }

    public List<Map> getListOfTopsFromSeverelPages(int maxPage) {
        List<Map> listOfTops = new ArrayList<Map>();
        listOfTops.addAll(getListOfTops(5));
        for (int pageNumber = 2; pageNumber <= maxPage; pageNumber++) {
            switchToResultPage(pageNumber);
            listOfTops.addAll(getListOfTops(5));
        }

        return listOfTops;
    }


}
