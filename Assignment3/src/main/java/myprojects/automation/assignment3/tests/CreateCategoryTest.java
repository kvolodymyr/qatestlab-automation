package myprojects.automation.assignment3.tests;

import myprojects.automation.assignment3.BaseScript;
import myprojects.automation.assignment3.GeneralActions;
import org.openqa.selenium.WebDriver;

public class CreateCategoryTest extends BaseScript {
    private static String email = "webinar.test@gmail.com";
    private static String password = "Xcg7299bnSmMuRLp9ITw";

    public static void main(String[] args) throws InterruptedException {
        // prepare driver object
        WebDriver driver = BaseScript.getConfiguredDriver();

        GeneralActions  actions = new GeneralActions(driver);

        actions.login(email, password);

        // create category
        actions.createCategory(GeneralActions.CategoryName);

        // check that new category appears in Categories table
        actions.checkCategory(GeneralActions.CategoryName);

        // delete category
        actions.deleteCategory(GeneralActions.CategoryName);

        driver.quit();
    }
}
