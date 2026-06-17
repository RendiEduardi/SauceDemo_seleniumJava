
package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginStep {

    @Given("User navigated to login page")
    public void user_navigated_to_login_page() {
        Hooks.driver.get("https://www.saucedemo.com/");
    }

    @When("User enters username {string}")
    public void user_enters_username(String username) {
        Hooks.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name"))).sendKeys(username);
    }

    @And("User enters password {string}")
    public void user_enters_password(String password) {
        Hooks.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password"))).sendKeys(password);
    }

    @And("User clicks login")
    public void user_clicks_login() {
        Hooks.wait.until(ExpectedConditions.elementToBeClickable(By.id("login-button"))).click();
    }

    @Then("User in homepage menu")
    public void user_in_homepage_menu() {
        boolean isDisplayed = Hooks.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inventory_container"))).isDisplayed();
        //Assert.assertTrue("User sukses masuk homepage", isDisplayed);
        Assert.fail("Sengaja dibuat gagal untuk test Allure");
    }

    @Then("User should see login error message {string}")
    public void user_should_see_login_error_message(String expectedMessage) {
        String actualMessage = Hooks.wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-test='error']"))).getText();
        Assert.assertEquals("Pesan error login tidak sesuai", expectedMessage, actualMessage);
    }

    @When("User adds product {string} to cart")
    public void user_adds_product_to_cart(String productName) {
        By addToCartButton = By.xpath("//div[contains(@class,'inventory_item')][.//div[contains(@class,'inventory_item_name') and normalize-space()='" + productName + "']]//button");
        WebElement button = Hooks.wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
        ((JavascriptExecutor) Hooks.driver).executeScript("arguments[0].scrollIntoView({block: 'center'}); arguments[0].click();", button);
        Hooks.wait.until(ExpectedConditions.textToBe(addToCartButton, "Remove"));
    }

    @When("User removes product {string} from cart")
    public void user_removes_product_from_cart(String productName) {
        By removeButton = By.xpath("//div[contains(@class,'inventory_item') or contains(@class,'cart_item')][.//div[contains(@class,'inventory_item_name') and normalize-space()='" + productName + "']]//button");
        WebElement button = Hooks.wait.until(ExpectedConditions.elementToBeClickable(removeButton));
        ((JavascriptExecutor) Hooks.driver).executeScript("arguments[0].scrollIntoView({block: 'center'}); arguments[0].click();", button);
        Hooks.wait.until(ExpectedConditions.textToBe(removeButton, "Add to cart"));
    }

    @Then("Cart badge should show {string}")
    public void cart_badge_should_show(String expectedCount) {
        String actualCount = Hooks.wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("shopping_cart_badge"))).getText();
        Assert.assertEquals("Jumlah item cart tidak sesuai", expectedCount, actualCount);
    }

    @Then("Cart badge should not be displayed")
    public void cart_badge_should_not_be_displayed() {
        boolean isNotDisplayed = Hooks.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("shopping_cart_badge")));
        Assert.assertTrue("Badge cart masih tampil", isNotDisplayed);
    }

    @When("User clicks menu cart")
    public void user_clicks_menu_cart() {
        WebElement cartLink = Hooks.wait.until(ExpectedConditions.elementToBeClickable(By.className("shopping_cart_link")));
        ((JavascriptExecutor) Hooks.driver).executeScript("arguments[0].scrollIntoView({block: 'center'}); arguments[0].click();", cartLink);
        Hooks.wait.until(ExpectedConditions.urlContains("cart.html"));
    }

    @Then("User should see product {string} in cart page")
    public void user_should_see_product_in_cart_page(String productName) {
        boolean isDisplayed = Hooks.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'inventory_item_name') and text()='" + productName + "']"))).isDisplayed();
        Assert.assertTrue("Produk tidak tampil di halaman cart", isDisplayed);
    }
}
