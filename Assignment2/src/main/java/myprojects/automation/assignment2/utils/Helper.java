package myprojects.automation.assignment2.utils;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

import static myprojects.automation.assignment2.BaseScript.getDriver;

public class Helper {
    public final static String Nickname = "webinar.test@gmail.com";
    public final static String Password = "Xcg7299bnSmMuRLp9ITw";

    public static WebDriver open(String url) {
        WebDriver driver = getDriver();
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);

        //driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        driver.get(url);
        sleep(1000);
        return driver;
    }

    public static void signIn(WebDriver driver, String email, String password) {
        driver
            .findElement(By.id("email"))
            .sendKeys("webinar.test@gmail.com");

        driver
            .findElement(By.id("passwd"))
            .sendKeys("Xcg7299bnSmMuRLp9ITw");

        driver
            .findElement(By.name("submitLogin"))
            .click();

        sleep(1000);

        // check the action was success
        driver.findElement(By.id("header_employee_box"));
    }

    public static void signOut(WebDriver driver) {
        WebElement menu = driver.findElement(By.id("header_employee_box"));
        menu.findElement(By.className("employee_name")).click();
        sleep(100);

        menu.findElement(By.id("header_logout")).click();
        sleep(2000);

        // check the action was success
        driver.findElement(By.name("submitLogin"));
    }

    public static void sleep(long ms)  {
        try {
            Thread.sleep(ms);// <-- WebDriverWait
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
