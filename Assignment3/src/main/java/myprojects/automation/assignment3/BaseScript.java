package myprojects.automation.assignment3;

import myprojects.automation.assignment3.utils.EventHandler;
import myprojects.automation.assignment3.utils.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * Base script functionality, can be used for all Selenium scripts.
 */
public abstract class BaseScript {
    /**
     *
     * @return New instance of {@link WebDriver} object. Driver type is based on passed parameters
     * to the automation project, returns {@link ChromeDriver} instance by default.
     */
    public static WebDriver getDriver() {
        String browser = Properties.getBrowser();
        switch (browser) {
            // prepare required WebDriver instance according to browser type

            case BrowserType.FIREFOX: {
                System.setProperty(
                    "webdriver.gecko.driver",
                    new File(BaseScript.class.getResource("/geckodriver.exe").getFile()).getPath());
                return new FirefoxDriver();
            }
            case BrowserType.IE: {
                System.setProperty(
                        "webdriver.ie.driver",
                        new File(BaseScript.class.getResource("/IEDriverServer.exe").getFile()).getPath());
                return new FirefoxDriver();
            }
            default:
                System.setProperty(
                    "webdriver.chrome.driver",
                    new File(BaseScript.class.getResource("/chromedriver.exe").getFile()).getPath());
                return new ChromeDriver();
        }
    }

    /**
     * Creates {@link WebDriver} instance with timeout and browser window configurations.
     *
     * @return New instance of {@link EventFiringWebDriver} object. Driver type is based on passed parameters
     * to the automation project, returns {@link ChromeDriver} instance by default.
     */
    public static EventFiringWebDriver getConfiguredDriver() {
        WebDriver driver = getDriver();
        //  configure browser window (set timeouts, browser pindow position) and connect loggers.
        driver.manage().window().maximize();
        // driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        // driver.manage().timeouts().pageLoadTimeout(4, TimeUnit.SECONDS);
        EventFiringWebDriver eventFiringWebDriver = new EventFiringWebDriver(driver);
        eventFiringWebDriver.register(new EventHandler());
        return eventFiringWebDriver;
    }
}
