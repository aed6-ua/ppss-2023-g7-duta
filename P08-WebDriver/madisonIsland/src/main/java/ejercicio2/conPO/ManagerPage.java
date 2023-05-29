package ejercicio2.conPO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ManagerPage {
    WebDriver driver;
    String pTitle;

    public ManagerPage(WebDriver driver){
        this.driver = driver;
        pTitle = driver.getTitle();
    }
}
