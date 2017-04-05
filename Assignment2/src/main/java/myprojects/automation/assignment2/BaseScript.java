package myprojects.automation.assignment2;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;

import java.io.File;


/**
 * Base script functionality, can be used for all Selenium scripts.
 */
public abstract class BaseScript {

    private static String BROWSER = BrowserType.CHROME;
    /**
     *
     * @return New instance of {@link WebDriver} object.
     */
    public static WebDriver getDriver() {
        switch(BROWSER) {
            case BrowserType.FIREFOX: {
                System.setProperty(
                    "webdriver.gecko.driver",
                    new File(BaseScript.class.getResource("/geckodriver.exe").getFile()).getPath());
                    return new FirefoxDriver();
            }
            default: {
                System.setProperty(
                    "webdriver.chrome.driver",
                    new File(BaseScript.class.getResource("/chromedriver.exe").getFile()).getPath());
                return new ChromeDriver();
                // ChromeOptions options = new ChromeOptions();
                // options.addArguments("--dns-prefetch-disable");
                // return new ChromeDriver(options);
            }
        }
    }

}
