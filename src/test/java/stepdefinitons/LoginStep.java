package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import junit.framework.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class LoginStep {

    WebDriver driver;

    @Given("User navigated to login page")
    public void user_navigated_to_login_page() {

        ChromeOptions options = new ChromeOptions();

        options.addArguments("--disable-notifications");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-save-password-bubble");
        options.addArguments("--disable-features=PasswordManagerEnabled");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
    }

    @When("User enters username {string}")
    public void user_enters_username(String username) {
        driver.findElement(By.id("user-name")).sendKeys(username);
    }

    @And("User enters password {string}")
    public void user_enters_password(String password) {
        driver.findElement(By.id("password")).sendKeys(password);
    }

    @And("User clicks login")
    public void user_clicks_login() {
        driver.findElement(By.id("login-button")).click();
    }

    @Then("User in homepage menu")
    public void user_in_homepage_menu() {
        //driver.findElement(By.id("inventory_containe")).isDisplayed();
        boolean isDisplayed = driver.findElement(By.id("inventory_container")).isDisplayed();
        Assert.assertTrue("User sukses masuk homepage", isDisplayed);
    }
}
