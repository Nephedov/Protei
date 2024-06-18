package pages;

import data.DataGenerator;
import io.qameta.allure.Allure;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class AuthPage {
    public WebDriver driver;
    public AuthPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "authPage")
    public WebElement authPage;
    @FindBy(id = "authButton")
    public WebElement submitButton;

    @FindBy(id = "loginEmail")
    public WebElement emailField;

    @FindBy(id = "loginPassword")
    public WebElement passwordField;

    @FindBy(css = "#authAlertsHolder>div")
    public WebElement alertMessage;

    @FindBy(css = ".uk-alert-close")
    public WebElement closeAlertMessageButton;

    public void clickOnCloseAlertMessageButton() {
        Allure.step("Click on CloseAlertMessageButton");
        closeAlertMessageButton.click();
    }
    public void enterEmail(String email) {
        Allure.step("Enter the value \"" + email + "\" in the email field");
        emailField.clear();
        emailField.sendKeys(email);
    }

    public void enterPassword(String password) {
        Allure.step("Enter the value \"" + password + "\" in the password field");
        passwordField.clear();
        passwordField.sendKeys(password);
    }
    public void clickOnSubmitButton() {
        Allure.step("Click on AuthButton");
        submitButton.click();
    }

    public void validAuthorization() {
        DataGenerator.User user = new DataGenerator().getValidUser();

        enterEmail(user.getLogin());
        enterPassword(user.getPassword());
        clickOnSubmitButton();
    }
}
