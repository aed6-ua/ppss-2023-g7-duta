package ejercicio3.conPOyPFact;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyAccountPage {
    WebDriver driver;
    @FindBy (xpath = "//*[@id=\"nav\"]/ol/li[3]/a") WebElement accessories;
    @FindBy (xpath = "//*[@id=\"nav\"]/ol/li[3]/ul/li[4]/a") WebElement shoes;

    public MyAccountPage(WebDriver driver) {
        this.driver = driver;
    }

    public ShoesPage shoes() {
        //movemos el ratón sobre el elemento "accessories"
        Actions builder = new Actions(driver);
        builder.moveToElement(accessories);
        builder.perform();
        //ahora serán visibles los hiperenlaces, y podremos clickar sobre "shoes"
        shoes.click();
        return PageFactory.initElements(driver, ShoesPage.class);
    }

    public String getTitle() {
        return driver.getTitle();
    }
}