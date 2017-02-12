package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by Tetiana on 05.02.2017.
 */

abstract class CommonTest {


    protected String targetURL = "http://rozetka.com.ua/";
    protected WebDriver driver;

     @BeforeClass
    void signIn() {
        createWebDriver();
        driver.navigate().to(targetURL);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
    }

    private void createWebDriver() {
        System.setProperty("webdriver.chrome.driver", "c:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe");
        System.out.println("Creating webDriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
    }

    @AfterClass(alwaysRun = true)
    void finalizeCommon() {
        driver.quit();
    }

    void writeResultsToDb(List<Map> resultsList) {
        String tableName = this.getClass().getName() + "_" + System.currentTimeMillis();
        // create a sql query string to create table for test results
        String createTestTable = "CREATE TABLE " + tableName + " ("
                + "idItem INTEGER NOT NULL AUTO_INCREMENT,";
        Set<String> setOfColumns = resultsList.get(0).keySet();
        for (String column : setOfColumns) {
            createTestTable += column + " TEXT,";
        }
        createTestTable += "PRIMARY KEY (idItem))";

        try {
            // create a mysql database connection
            String myDriver = "org.gjt.mm.mysql.Driver";
            String myUrl = "jdbc:mysql://localhost:3306/test";
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(myUrl, "root", "humbert");
            Statement st = conn.createStatement();
            // execute SQL query to create table
            st.executeUpdate(createTestTable);
            String listOfColumns = " (";
            for (String column : setOfColumns) {
                listOfColumns = listOfColumns + ", " + column;
            }
            listOfColumns = listOfColumns.replaceFirst(",", " ");
            listOfColumns += " )";
            // create a sql query string to insert test results to table
            for (Map item : resultsList) {
                String insertTestTable = "";
                for (String column : setOfColumns)
                    insertTestTable = insertTestTable + ", " + "'" + item.get(column) + "'";
                insertTestTable = insertTestTable.replaceFirst(",", " ");
                insertTestTable = "INSERT INTO " + tableName + listOfColumns + " VALUES ( " + insertTestTable;
                insertTestTable += " )";
                // execute SQL query to insert data into table
                st.executeUpdate(insertTestTable);
            }
            conn.close();
        } catch (Exception e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }

    }

}