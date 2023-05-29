package ejercicio2.conPO;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class TestLogin2 {
    WebDriver driver;
    LoginPage poLogin;
    ManagerPage poManagerPage;
    @BeforeEach
    public void setup(){
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        poLogin = new LoginPage(driver);
    }

    @Test
    public void test_Login_Correct(){

        Assertions.assertEquals("Customer Login", poLogin.pTitle);
        poManagerPage = poLogin.login("edu.duta96@gmail.com", "passwordExample");

        Assertions.assertEquals("My Account", poManagerPage.pTitle);
    }

    @Test
    public  void test_Login_Incorrect() {
        Assertions.assertEquals("Customer Login", poLogin.pTitle);
        poLogin.loginIncorrect("edu.duta96@gmail.com", "passwordxample");

        Assertions.assertEquals("Invalid login or password.", poLogin.incorrectLogin.getText());
    }
    @AfterEach
    public void tearDown() {
        driver.close();
    }

}
