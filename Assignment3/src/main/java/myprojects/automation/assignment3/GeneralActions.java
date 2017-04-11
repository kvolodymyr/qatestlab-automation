package myprojects.automation.assignment3;

import myprojects.automation.assignment3.utils.Properties;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Contains main script actions that may be used in scripts.
 */
public class GeneralActions {
    public static String CategoryName = "17-03-22-W-KulibabaV";

    private WebDriver driver;
    private WebDriverWait wait;
    // login page
    private By emailInput = By.id("email");
    private By passwordInput = By.id("passwd");
    private By submitButton = By.name("submitLogin");
    // categories main page
    private By catalogMenuItem = By.id("subtab-AdminCatalog");
    private By categoriesSubMenuItem = By.id("subtab-AdminCategories");
    private By newCategoryButton = By.id("page-header-desc-category-new_category");
    private By categoryCreatedSuccessAlert = By.xpath("//*[@id='content']/div[contains(@class, 'bootstrap')]/div[contains(@class, 'alert') and contains(@class, 'alert-success')][contains(., 'Создано')]");
    private By filterByCategoryName = By.name("categoryFilter_name");
    private By filterApplyButton = By.id("submitFilterButtoncategory");
    private By filterResetButton = By.name("submitResetcategory");
    private String tableRowWithAddedCategoryMask = "//table[@id='table-category']//tr[contains(td[3],'%1$s')]";

    private By actionsToggleButton = By.className("dropdown-toggle");
    private By actionsDeleteButton = By.className("delete");
    private By confirmDeleteForm = By.cssSelector(".leadin form");
    private By choiceDeleteMode = By.id("deleteMode_delete");
    private By categoryDeletedSuccessAlert = By.xpath("//*[@id='content']/div[contains(@class, 'bootstrap')]/div[contains(@class, 'alert') and contains(@class, 'alert-success')][contains(., 'Удаление завершено')]");

    // category editor
    private By nameInput = By.id("name_1");
    private By categorySaveButton = By.id("category_form_submit_btn");

    public GeneralActions(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 4);
    }

    /**
     * Logs in to Admin Panel.
     * @param login
     * @param password
     */
    public void login(String login, String password) {
        driver.get(Properties.getBaseAdminUrl());

        driver
            .findElement(emailInput)
            .sendKeys(login);

        driver
            .findElement(passwordInput)
            .sendKeys(password);

        driver
            .findElement(submitButton)
            .click();
    }

    /**
     * Adds new category in Admin Panel.
     * @param categoryName
     */
    public void createCategory(String categoryName) {
        WebElement element;

        WebElement menuItem = waitForContentLoad(catalogMenuItem);
        Actions action = new Actions(driver);
        action.moveToElement(menuItem).perform();
        action.moveToElement(driver.findElement(categoriesSubMenuItem)).click().build().perform();

        element = waitForContentLoad(newCategoryButton);
        element.click();

        driver
            .findElement(nameInput)
            .sendKeys(categoryName);

        // driver.findElement(By.tagName("body")).click().sendKeys("YOOOO");
        //  ((JavascriptExecutor)driver).executeScript(
        // "tinyMCE.activeEditor.setContent('<p><i>Lorem ipsum dolor sit amet, te commodo epicuri praesent mei. Vis ne sale invidunt adipisci. Duo id postea consequat. Eum eu phaedrum vituperata scriptorem. Tale inciderint ad his, ad lucilius dissentiunt pri. Cu congue aliquip mediocritatem mel, ad eum ignota phaedrum, ut est minimum insolens. Ne sit accumsan conceptam, option dolores ocurreret eu per.</i>')");
        driver.switchTo().frame("description_1_ifr");
        element = driver.findElement(By.cssSelector("body"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].innerHTML = '<h1>Lorem ipsum dolor sit amet</h1><p><i>Lorem ipsum dolor sit amet, te commodo epicuri praesent mei. Vis ne sale invidunt adipisci. Duo id postea consequat. Eum eu phaedrum vituperata scriptorem. Tale inciderint ad his, ad lucilius dissentiunt pri. Cu congue aliquip mediocritatem mel, ad eum ignota phaedrum, ut est minimum insolens. Ne sit accumsan conceptam, option dolores ocurreret eu per.</i></p>'", element);
        driver.switchTo().defaultContent();

        driver
            .findElement(categorySaveButton)
            .click();

        waitForContentLoad(categoryCreatedSuccessAlert);
    }

    /**
     * Check category in Admin Panel.
     * @param categoryName
     */
    public void checkCategory(String categoryName) {
        // By categoryInTableLocator = By.xpath("//table[@id='table-category']//td[3][contains(.,'" + categoryName + "')]");

        driver.findElement(filterByCategoryName).sendKeys(categoryName);
        driver.findElement(filterApplyButton).click();
        waitForContentLoad(filterResetButton);
        driver.findElement(By.xpath(String.format(tableRowWithAddedCategoryMask, categoryName)));
    }


    /**
     * Check category in Admin Panel.
     * @param categoryName
     */
    public void deleteCategory(String categoryName) {
        WebElement element = driver.findElement(By.xpath(String.format(tableRowWithAddedCategoryMask, categoryName)));
        element.findElement(actionsToggleButton).click();
        element.findElement(actionsDeleteButton).click();
        element = waitForContentLoad(confirmDeleteForm);
        element.findElement(choiceDeleteMode).click();
        element.submit();
        waitForContentLoad(categoryDeletedSuccessAlert);
    }

    /**
     * Waits until page loader disappears from the page
     */
    public WebElement waitForContentLoad(By locator) {
        // implement generic method to wait until page content is loaded
         return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void createCategoryJS(String categoryName) {
        // wait.until(ExpectedConditions.visibilityOfElementLocated(bySubAdminCatalogId));
        waitForContentLoad(catalogMenuItem);
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        WebElement categoryiesLink = driver.findElement(newCategoryButton);
        boolean expectedResult = (boolean)executor.executeScript(
            "var scrollBefore = $(window).scrollTop();" +
            "window.scrollTo(scrollBefore, document.body.scrollHeight)" +
            "return $(window).scrollTop() > scrollBefore;");
        executor.executeScript("arguments[0].click();", categoryiesLink); // <--- DOESN'T WORK
    }
}
