package test;

import org.testng.annotations.Test;
import pageobject.*;

import java.util.List;
import java.util.Map;

/**
 * Created by Tetiana on 05.02.2017.
 */
public class TestTopSmartphones extends CommonTest {
    @Test
    public void run() {
        MainPage mainPage = new MainPage(driver, "ua");
        mainPage.clickMenu();

        TelephoneAndElectronicDevicesPage telephoneAndElectronicDevicesPage = new TelephoneAndElectronicDevicesPage(driver, "telefony-tv-i-ehlektronika");
        telephoneAndElectronicDevicesPage.clickMenu();

        TelephonePage telephonePage = new TelephonePage(driver, "telefony");
        telephonePage.clickMenu();

        SmartphonePage smartphonePage = new SmartphonePage(driver, "smartfon");
        List<Map> listOfTops = smartphonePage.getListOfTopsFromSeveralPages(3);

        System.out.println("Was found: " + listOfTops.size() + "top-smartphones");
        writeResultsToDb(listOfTops);

    }
}
