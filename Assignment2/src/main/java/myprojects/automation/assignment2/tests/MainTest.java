package myprojects.automation.assignment2.tests;

import myprojects.automation.assignment2.BaseScript;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class MainTest extends BaseScript {
    private static String BASE_URL = "http://prestashop-automation.qatestlab.com.ua";

    public static void main(String[] args)  {

        String successMessage = "Товар добавлен в корзину";

        WebDriver driver = getDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(2, TimeUnit.SECONDS);

        driver.get(BASE_URL);
        sleep(1000);


        By by = By.className("thumbnail-container");
        WebElement element = driver.findElement(by);

        List<WebElement> elements = driver.findElements(By.className("thumbnail-container"));

        WebElement webElement = elements.get(new Random().nextInt(elements.size()));
        String a = webElement.findElement(By.tagName("h1")).getText();

        WebElement s = driver.findElement(By.name("s"));

        sleep(1000);

        s.sendKeys(a);
        s.sendKeys(Keys.ENTER);

        sleep(1000);

        List<WebElement> results = driver.findElements(By.className("thumbnail-container"));
        WebElement webElement1 = results.get(new Random().nextInt(results.size()));
        webElement1.click();

        WebElement add = driver.findElement(By.className("product-add-to-cart"))
                .findElement(By.className("add"))
                .findElement(By.tagName("button"));

        add.click();

        sleep(1000);

        String myModalLabel = driver.findElement(By.id("myModalLabel")).getText();
        System.out.println(myModalLabel.contains(successMessage));

        WebElement close = driver.findElement(By.className("close"));
        close.click();

        sleep(1000);

        WebElement desktop_cart = driver.findElement(By.id("_desktop_cart"));
        desktop_cart.click();

        sleep(1000);

        List<WebElement> items = driver.findElements(By.className("cart-items"));
        if(items.size() > 0 )
            System.out.println("Items size = " + items.size());

        driver.quit();
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
