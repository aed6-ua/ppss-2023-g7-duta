package ejercicio3.conPOyPFact;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Set;

public class ShoesPage {
    WebDriver driver;
    @FindBy (css = "head > title") WebElement title;
    @FindBy (xpath = "//*[@id=\"top\"]/body/div/div[2]/div[2]/div/div[2]/div[2]/div[3]/ul/li[5]/div/div[2]/ul/li[2]/a") WebElement wingtipShoe;
    @FindBy (xpath = "//*[@id=\"top\"]/body/div/div[2]/div[2]/div/div[2]/div[2]/div[3]/ul/li[6]/div/div[2]/ul/li[2]/a") WebElement suedeShoe;
    @FindBy (xpath = "//*[@id=\"top\"]/body/div/div[2]/div[2]/div/div[3]/div[2]/div[2]/div/button") WebElement compareButton;
    @FindBy (xpath = "//*[@id=\"top\"]/body/div/div[2]/div[2]/div/div[3]/div/div[2]/div/a") WebElement clearAll;
    @FindBy (xpath = "//*[@id=\"top\"]/body/div/div[2]/div[2]/div/div[2]/div[2]/ul/li/ul/li/span") WebElement comparisonListCleared;

    public ShoesPage(WebDriver driver) {
        this.driver = driver;
    }
    public Products compareShoes() {
        selectShoeToCompare(5);
        selectShoeToCompare(6);

        //obtenemos el manejador de la ventana ShoesPage
        String myHandleId = driver.getWindowHandle();
        //pulsamos sobre el botón para hacer la comparación
        compareButton.click(); //se abre una nueva ventana
        //el handleID de la nueva ventana se añade al conjunto de manejadores del navegador
        Set<String> setIds = driver.getWindowHandles();
        String[] handleIds = setIds.toArray(new String[setIds.size()]);
        System.out.println("ID 0: "+handleIds[0]); //manejador de la ventana ShoesPage
        System.out.println("ID 1: "+handleIds[1]); //manejador de la venana ProductComparisonPage
        driver.switchTo().window(handleIds[1]);

        Products comparisonPage = PageFactory.initElements(driver, Products.class);
        comparisonPage.setMyHandleID(handleIds[1]);
        comparisonPage.setMyHandleIDFrom(handleIds[0]);
        return comparisonPage;
    }
    public void selectShoeToCompare(int number) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        switch(number) {
            case 5: jse.executeScript("arguments[0].scrollIntoView();", wingtipShoe);
                wingtipShoe.click();
                break;
            case 6: jse.executeScript("arguments[0].scrollIntoView();", suedeShoe);
                suedeShoe.click();
                break;
        }
    }

    public String clearComparisonAlert() {
        String myHandleId = driver.getWindowHandle();
        clearAll.click();
        //cambiamos el foco a la ventana de alerta
        Alert alert = driver.switchTo().alert();
        //podemos obtener el mensaje de la ventana
        String mensaje = alert.getText();
        //podemos pulsar sobre el botón OK (si lo hubiese)
        alert.accept();
        driver.switchTo().window(myHandleId);
        return mensaje;
    }

    public String checkComparisonCleared() {
        return comparisonListCleared.getText();
    }

    public String getTitle() {
        return driver.getTitle();
    }
}
