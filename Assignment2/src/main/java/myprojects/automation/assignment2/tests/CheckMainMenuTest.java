package myprojects.automation.assignment2.tests;

import myprojects.automation.assignment2.BaseScript;
import myprojects.automation.assignment2.utils.Helper;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class CheckMainMenuTest extends BaseScript {
    private static String BASE_URL = "http://prestashop-automation.qatestlab.com.ua/admin147ajyvk0/";
    private static int TIMEOUT = 2000;
    private static WebDriver driver = null;

    public static void main(String[] args) throws InterruptedException {
        // Script to check Main Menu items
        driver = Helper.open(BASE_URL);

        Helper.signIn(driver, "webinar.test@gmail.com","Xcg7299bnSmMuRLp9ITw");

        // validate the Menu Item
        open("tab-AdminDashboard", 1,"Dashboard", "page-head","Dashboard",  "page-title", "Пульт");
        open("subtab-AdminParentOrders", 3, "Заказы", "page-head", "Заказы", "page-title", "Заказы");
        // possible issue: menu structure was changed
        open("subtab-AdminCatalog", 9, "Каталог", "header-toolbar", "Каталогтовары", "title", "товары");
        home(); // workaround: go to home page to fix the found issue
        open("subtab-AdminParentCustomer", 23, "Клиенты", "page-head", "Клиенты", "page-title", "Управление клиентами");
        open("subtab-AdminParentCustomerThreads", 27, "Служба поддержки", "page-head","СлужбаподдержкиCustomerService", "page-title", "Customer Service");
        open("subtab-AdminStats", 31,"Статистика", "page-head", "SellСтатистика", "page-title", "Статистика");
        // possible issue: menu structure was changed
        open("subtab-AdminParentModulesSf", 42, "Modules", "header-toolbar","Modules", "title", "Выбор модуля");
        home(); // workaround: go to home page to fix the found issue
        open("subtab-AdminParentThemes", 46,"Design", "page-head", "DesignШаблоны", "page-title", "Шаблоны > Шаблон");
        open("subtab-AdminParentShipping", 52,"Доставка", "page-head","ДоставкаКурьеры", "page-title", "Перевозчики");
        open("subtab-AdminParentPayment", 55,"Способ оплаты", "page-head","СпособоплатыPaymentMethods", "page-title", "Payment Methods");
        open("subtab-AdminInternational", 58, "International", "page-head","Локализация", "page-title", "Локализация");
        open("subtab-ShopParameters", 73,"Shop Parameters", "page-head","ОбщиенастройкиGeneral", "page-title", "General");
        open("subtab-AdminAdvancedParameters", 95, "Конфигурация", "page-head","КонфигурацияInformation", "page-title", "Information");

        driver.quit();
    }

    private static void open(String txtMenuKey, int iMenuId, String txtMenuName, String txtPageKey, String txtBreadCrumbName, String txtPageTitleKey, String txtPageTitle) throws InterruptedException {
        System.out.printf("Validate MenuItem: \"%s\"\n", txtMenuName);

        WebElement elMenuItem = driver.findElement(By.id(txtMenuKey));
        Assert.assertEquals(txtMenuName, elMenuItem.getText().trim());
        // WebElement link = elMenuItem.findElement(By.linkText(txtMenuName));
        // link.click();
        System.out.printf("Page: \"%s\" ---> \"%s\"\n", driver.getTitle(), elMenuItem.getText());
        elMenuItem.findElement(By.tagName("a")).click();
        // Helper.sleep(TIMEOUT);

        driver.navigate().refresh();
        // Helper.sleep(TIMEOUT);

        // re-attach element to document
        elMenuItem = driver.findElement(By.cssSelector("[data-submenu=\"" + iMenuId + "\"]"));
        assertHasAnyClass(elMenuItem, new String[] { "active", "-active" } );

        WebElement elHeaderContainer = driver.findElement(By.className(txtPageKey));
        WebElement elBreadCrumb = elHeaderContainer.findElement(By.className("breadcrumb"));
        Assert.assertEquals(txtBreadCrumbName, elBreadCrumb.getText().replaceAll("\\s+",""));

        WebElement elPageTitle = elHeaderContainer.findElement(By.className(txtPageTitleKey));
        Assert.assertEquals(txtPageTitle, elPageTitle.getText());

        System.out.printf("MenuItem: \"%s\", BreadCrumb: \"%s\", PageTitle: \"%s\"\n",
                elMenuItem.getText().replaceAll("\\s+",""),
                elBreadCrumb.getText().replaceAll("\\s+",""),
                elPageTitle.getText().replaceAll("\\s+",""));
    }

    private static void home() {
        driver.findElement(By.className("logo")).click();
        // Helper.sleep(TIMEOUT);
    }

    private static void assertHasAnyClass(WebElement element, String[] listOfClasses) {
        List<String> lstClass = java.util.Arrays.asList(element.getAttribute("class").split(" "));
        boolean result = false;
        for (String item : listOfClasses) {
            result |= lstClass.indexOf(item) != -1;
        }
        Assert.assertTrue(result);
    }
}