package myprojects.automation.assignment2.tests;

import myprojects.automation.assignment2.BaseScript;
import myprojects.automation.assignment2.utils.Helper;
import myprojects.automation.assignment2.utils.Properties;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginTest extends BaseScript {

    // Script to execute login and logout steps
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = Helper.open(Properties.getBaseAdminUrl());

        Helper.signIn(driver, Helper.Nickname, Helper.Password);

        Helper.signOut(driver);

        driver.quit();
    }
}
