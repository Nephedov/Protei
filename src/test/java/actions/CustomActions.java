package actions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;


public class CustomActions {
    public static void hoverOnElement(WebDriver driver, WebElement element) {
        new Actions(driver).moveToElement(element).perform();
    }
    public static void clickOnElement (WebDriver driver, WebElement element) {
        new Actions(driver).moveToElement(element).click().perform();
    }

    public static boolean isExist(WebDriver driver, By by) {
        return !driver.findElements(by).isEmpty();
    }
}
