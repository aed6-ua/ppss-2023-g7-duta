package ejercicio3.conPOyPFact;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Products {
    WebDriver driver;
    String myHandleId;
    String myHandleIdFROM;
    @FindBy (xpath = "//*[@id=\"top\"]/body/div/div[3]/button") WebElement closeButton;

    public Products(WebDriver driver) {
        this.driver = driver;
    }

    public void setMyHandleID(String handleID) {
        this.myHandleId = handleID;
    }

    public void setMyHandleIDFrom(String handleID) {
        this.myHandleIdFROM = handleID;
    }

    public ShoesPage close() {
        //desde ProductComparisonPage...
        closeButton.click();
        driver.switchTo().window(myHandleIdFROM);
        return PageFactory.initElements(driver,ShoesPage.class);
    }

    public String getTitle() {
        return driver.getTitle();
    }
}
