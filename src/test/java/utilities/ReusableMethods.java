package utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ReusableMethods {


    public static void hoverover(WebElement element) {
        Actions actions = new Actions(Driver.getDriver());
        actions.moveToElement(element).perform();
    }
    public static WebElement webElementBul(By by){
        WebDriver driver= Driver.getDriver();
        return driver.findElement(by);

    }

}
