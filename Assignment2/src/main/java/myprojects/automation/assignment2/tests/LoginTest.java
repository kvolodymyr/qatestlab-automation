package myprojects.automation.assignment2.tests;

import myprojects.automation.assignment2.BaseScript;
import myprojects.automation.assignment2.utils.Helper;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginTest extends BaseScript {
    private static String BASE_URL = "http://prestashop-automation.qatestlab.com.ua/admin147ajyvk0/";

    public static void main(String[] args) throws InterruptedException {
        // Script to execute login and logout steps

        WebDriver driver = Helper.open(BASE_URL);

        Helper.signIn(driver, Helper.Nickname, Helper.Password);

        Helper.signOut(driver);

        driver.quit();
    }
}
