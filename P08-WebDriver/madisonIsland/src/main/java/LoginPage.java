import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
public class LoginPage {
    WebDriver driver;
    WebElement email;
    WebElement password;
    WebElement login;
    String pTitle;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.driver.get("http://demo-store.seleniumacademy.com/customer/account/login/");
        email = driver.findElement(By.name("email"));
        password =
                driver.findElement(By.name("pass"));
        login =
                driver.findElement(By.name("send2"));
        pTitle =
                driver.getTitle();
    }



}
