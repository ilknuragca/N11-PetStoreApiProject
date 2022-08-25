package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Driver {
    //create and initialize the driver instance
    private static WebDriver driver;

    private static int timeout = 5;

    private Driver(){
        //don't want to create another object. use Singleton pattern
    }

    //to initialize the driver we create a static method
    public static WebDriver getDriver() {
        //create the driver if and only if it is null
        if (driver == null) {
            String browser = ConfigReader.getProperty("browser");
            if ("chrome".equals(browser)) {
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
            } else if ("firefox".equals(browser)) {
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
            } else if ("ie".equals(browser)) {
                WebDriverManager.iedriver().setup();
                driver = new InternetExplorerDriver();
            } else if ("safari".equals(browser)) {
                WebDriverManager.getInstance(SafariDriver.class).setup();
                driver = new SafariDriver();
            } else if ("chrome-headless".equals(browser)) {
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver(new ChromeOptions().setHeadless(true));
            }
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
        return driver;
    }

    public static void closeDriver() {
        if (driver != null) {           //if the driver is pointing chrome
            driver.quit();              //quit the driver
            driver = null;              //set it back to null to make sure driver is null

            // important especially do cross browser testing
        }
    }

    public static void wait(int secs) {
        try {
            Thread.sleep(1000 * secs);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (java.util.NoSuchElementException e) {
            e.printStackTrace();
        } catch (StaleElementReferenceException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Wait And Click for WebElement
    public static void waitAndClick(WebElement element) {
        for (int i = 0; i < timeout; i++) {
            try {
                element.click();
                return;
            } catch (WebDriverException e) {
                wait(1);
            }
        }
    }

    //Wait And Send Text for WebElement
    public static void waitAndSendText(WebElement element, String text) {
        for (int i = 0; i < timeout; i++) {
            try {
                element.sendKeys(text);
                return;
            } catch (WebDriverException e) {
                wait(1);
            }
        }
    }

    //Wait For Visibility of WebElement
    public static WebElement waitForVisibility(WebElement element, int timeToWaitInSec) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    //Wait For Visibility of Locator
    public static WebElement waitForVisibility(By locator, int timeout) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    //Wait For Clickablility of WebElement
    public static WebElement waitForClickablility(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    //Wait For Clickablility of Locator
    public static WebElement waitForClickablility(By locator, int timeout) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }


    //Use for JavascriptExecutor
    public static void executeJScommand(WebElement element, String command) {
        JavascriptExecutor jse = (JavascriptExecutor) Driver.getDriver();
        jse.executeScript(command, element);
    }

    //Select An Item From Dropdown
    public static void selectAnItemFromDropdown(WebElement item, String selectableItem) {
        wait(5);
        Select select = new Select(item);
        for (int i = 0; i < select.getOptions().size(); i++) {
            if (select.getOptions().get(i).getText().equalsIgnoreCase(selectableItem)) {
                select.getOptions().get(i).click();
                break;
            }
        }

    }


    //Click on an element using JavaScript
    public static void clickWithJS(WebElement element) {
        ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
        ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].click();", element);
    }

    //Performs double click action on an element
    public static void doubleClick(WebElement element) {
        new Actions(Driver.getDriver()).doubleClick(element).build().perform();
    }

    //Select By Visible Text From DropDown
    public static void selectByVisibleText(WebElement element, String text) {
        Select objSelect = new Select(element);
        objSelect.selectByVisibleText(text);
    }


    //Select By Index From DropDown
    public static void selectByIndex(WebElement element, int index) {
        Select objSelect = new Select(element);
        objSelect.selectByIndex(index);
    }

    //Select By Value From DropDown
    public static void selectByValue(WebElement element, String value) {
        Select objSelect = new Select(element);
        List<WebElement> elementCount = objSelect.getOptions();
        objSelect.selectByValue(value);
        System.out.println("number of elements: " + elementCount.size());
    }

    //Use of Thread.sleep
    public static void sleep(int timeOut) {
        try {
            Thread.sleep(timeOut);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void waitAndClickLocationText(WebElement element, String value) {
        Driver.getDriver().findElement(By.xpath("//*[text()='" + value + "']")).click();
    }
}
