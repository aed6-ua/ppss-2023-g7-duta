package Ejercicio1.sinPageObject;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class TestLogin {
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
    public void loginOK() {
        Assertions.assertEquals("Madison Island", driver.getTitle());
        driver.findElement(By.cssSelector("#header > div > div.skip-links > div > a")).click();
        driver.findElement(By.cssSelector("#header-account > div > ul > li.last > a")).click();
        Assertions.assertEquals("Customer Login", driver.getTitle());
        driver.findElement(By.id("email")).sendKeys("edu.duta96@gmail.com");
        driver.findElement(By.id("send2"));
        driver.findElement(By.id("advice-required-entry-pass")).isDisplayed();
        driver.findElement(By.id("pass")).sendKeys("passwordExample");
        driver.findElement(By.id("send2"));
        Assertions.assertEquals("My Account", driver.getTitle());
    }

    @Test
    public void loginFailed() {
        Assertions.assertEquals("Madison Island", driver.getTitle());
        driver.findElement(By.cssSelector("#header > div > div.skip-links > div > a")).click();
        driver.findElement(By.cssSelector("#header-account > div > ul > li.last > a")).click();
        Assertions.assertEquals("Customer Login", driver.getTitle());
        driver.findElement(By.id("email")).sendKeys("edu.duta96@gmail.com");
        driver.findElement(By.id("pass")).sendKeys("passwordxample");
        driver.findElement(By.id("send2"));
        Assertions.assertEquals("Invalid login or password", driver.findElement(By.cssSelector("body > div > div.page > div.main-container.col1-layout > div > div > div.account-login > ul > li > ul > li > span")).getText());
    }
}
