
package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class LoginStep {

    WebDriver driver;
    WebDriverWait wait;

    @Given("User navigated to login page")
    public void user_navigated_to_login_page() {
        ChromeOptions options = new ChromeOptions();

        options.addArguments("--disable-notifications");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-save-password-bubble");
        options.addArguments("--disable-features=PasswordManagerEnabled");
        options.addArguments("--guest");

        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("profile.password_manager_leak_detection", false);
        options.setExperimentalOption("prefs", prefs);

        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
    }

    @When("User enters username {string}")
    public void user_enters_username(String username) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name"))).sendKeys(username);
    }

    @And("User enters password {string}")
    public void user_enters_password(String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password"))).sendKeys(password); //note
    }

    @And("User clicks login")
    public void user_clicks_login() {
        wait.until(ExpectedConditions.elementToBeClickable(By.id("login-button"))).click();
    }

    @Then("User in homepage menu")
    public void user_in_homepage_menu() {
        boolean isDisplayed = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inventory_container"))).isDisplayed();
        Assert.assertTrue("User sukses masuk homepage", isDisplayed);
    }

    @When("User adds product {string} to cart")
    public void user_adds_product_to_cart(String productName) {
        By addToCartButton = By.xpath("//div[contains(@class,'inventory_item')][.//div[contains(@class,'inventory_item_name') and normalize-space()='" + productName + "']]//button");
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'}); arguments[0].click();", button);
        wait.until(ExpectedConditions.textToBe(addToCartButton, "Remove"));
    }

    @Then("Cart badge should show {string}")
    public void cart_badge_should_show(String expectedCount) {
        String actualCount = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("shopping_cart_badge"))).getText();
        Assert.assertEquals("Jumlah item cart tidak sesuai", expectedCount, actualCount);
    }

    @When("User clicks menu cart")
    public void user_clicks_menu_cart() {
        WebElement cartLink = wait.until(ExpectedConditions.elementToBeClickable(By.className("shopping_cart_link")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'}); arguments[0].click();", cartLink);
        wait.until(ExpectedConditions.urlContains("cart.html"));
    }

    @Then("User should see product {string} in cart page")
    public void user_should_see_product_in_cart_page(String productName) {
        boolean isDisplayed = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'inventory_item_name') and text()='" + productName + "']"))).isDisplayed();
        Assert.assertTrue("Produk tidak tampil di halaman cart", isDisplayed);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
