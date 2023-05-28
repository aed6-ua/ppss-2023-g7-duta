package Ejercicio1.sinPageObject;

import dev.failsafe.internal.util.Assert;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class TestCreateAccount {
    WebDriver driver;
    @BeforeEach
    public void setUp() {
        driver  = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("http://demo-store.seleniumacademy.com/");
    }

    @AfterEach
    public void tearDown() {
        driver.close();
    }

    @Test
    @Tag("OnlyOnce")
    public void createAccount() {
        Assertions.assertEquals("Madison Island", driver.getTitle());
        driver.findElement(By.cssSelector("#header > div > div.skip-links > div > a")).click();
        driver.findElement(By.cssSelector("#header-account > div > ul > li.last > a")).click();
        Assertions.assertEquals("Customer Login", driver.getTitle());
        driver.findElement(By.cssSelector("#login-form > div > div.col-1.new-users > div.buttons-set > a")).click();
        Assertions.assertEquals("Create New Customer Account", driver.getTitle());
        driver.findElement(By.id("firstname")).sendKeys("edu");
        driver.findElement(By.id("lastname")).sendKeys("edu");
        driver.findElement(By.id("email_address")).sendKeys("edu.duta96@gmail.com");
        driver.findElement(By.id("password")).sendKeys("passwordExample");
        driver.findElement(By.id("form-validate")).submit();
        Assertions.assertTrue(driver.findElement(By.id("advice-required-entry-confirmation")).isDisplayed());
        driver.findElement(By.id("confirmation")).sendKeys("passwordExample");
        driver.findElement(By.id("form-validate")).submit();
        Assertions.assertEquals("My Account", driver.getTitle());
    }
}
