package ejercicio2.conPO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
public class LoginPage {
    WebDriver driver;
    WebElement email;
    WebElement password;
    WebElement login;
    String pTitle;
    WebElement incorrectLogin;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.driver.get("http://demo-store.seleniumacademy.com/customer/account/login/");
        email = driver.findElement(By.id("email"));
        password =
                driver.findElement(By.id("pass"));
        login =
                driver.findElement(By.id("send2"));
        pTitle =
                driver.getTitle();
    }

    public ManagerPage login(String user, String pass) {
        email.sendKeys(user);
        password.sendKeys(pass);
        login.click();
        return new ManagerPage(driver);
    }

    public void loginIncorrect(String user, String pass) {
        email.sendKeys(user);
        password.sendKeys(pass);
        login.click();
        incorrectLogin = driver.findElement(By.cssSelector("body > div > div.page > div.main-container.col1-layout > div > div > div.account-login > ul > li > ul > li > span"));
    }

}
