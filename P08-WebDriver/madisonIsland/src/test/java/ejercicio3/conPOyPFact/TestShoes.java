package ejercicio3.conPOyPFact;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class TestShoes {
    WebDriver driver;
    MyAccountPage poAccount;
    ShoesPage poShoes;
    Products poComparison;

    @BeforeAll
    public static void storeCookies() {
        Cookies.storeCookiesToFile("edu.duta96@gmail.com", "passwordExample");
    }

    @BeforeEach
    public void setUp() {
        ChromeOptions chromeOptions = new ChromeOptions();
        //recuperamos el valor de la propiedad chromeHeadless definida en surefire
        boolean headless = Boolean.parseBoolean(System.getProperty("chromeHeadless"));
        //el método setHeadless() cambia la configuración de Chrome a modo headless
        chromeOptions.setHeadless(headless);
        //ahora creamos una instancia de CromeDriver a partir de chromeOptions
        driver = new ChromeDriver(chromeOptions);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        Cookies.loadCookiesFromFile(driver);
        driver.get("http://demo-store.seleniumacademy.com/customer/account/");
        poAccount = PageFactory.initElements(driver, MyAccountPage.class);
    }

    @AfterEach
    public void tearDown() {
        driver.close();
    }

    @Test
    public void testShoes() {
        Assertions.assertEquals("My Account", poAccount.getTitle());
        poShoes = poAccount.shoes();
        Assertions.assertEquals("Shoes - Accessories", poShoes.getTitle());
        poComparison = poShoes.compareShoes();
        Assertions.assertEquals("Products Comparison List - Magento Commerce", poComparison.getTitle());
        poShoes = poComparison.close();
        Assertions.assertEquals("Shoes - Accessories", poShoes.getTitle());
        Assertions.assertEquals("Are you sure you would like to remove all products from your comparison?", poShoes.clearComparisonAlert());
        Assertions.assertEquals("The comparison list was cleared.", poShoes.checkComparisonCleared());
    }
}
