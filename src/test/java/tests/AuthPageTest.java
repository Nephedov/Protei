package tests;

import actions.CustomActions;
import actions.DriverConfig;
import data.DataGenerator;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.AuthPage;

import java.time.Duration;

import static org.testng.Assert.*;

public class AuthPageTest {

    public WebDriver driver;
    public WebDriverWait wait;

    @BeforeMethod(description = "Open html")
    public void setup() {
        driver = new DriverConfig().setUpDriverChrome();
        wait = new WebDriverWait(driver, Duration.ofSeconds(3)); // Wait up to 3 seconds


    }

    @AfterMethod(description = "Close html")
    public void tearDown() {
        driver.quit();
        driver = null;
    }

    @Epic("UI testing")
    @Feature("Authorization Page")
    @Story("Email field")
    @Test(description = "Check email field is displayed")
    @Description("Should check that email field is displayed")
    public void shouldCheckEmailFieldIsDisplayed() {
        AuthPage loginPage = new AuthPage(driver);
        String expected = "E-Mail:";

        assertTrue(loginPage.emailField.isDisplayed());
        assertEquals(loginPage.emailField.getAccessibleName(), expected);
    }

    @Epic("UI testing")
    @Feature("Authorization Page")
    @Story("Email field")
    @Test(description = "Check email field changes color on click")
    @Description("Should check that email field changes color on click")
    public void shouldCheckEmailFieldChangesColorOnClick() throws InterruptedException {
        AuthPage loginPage = new AuthPage(driver);
        String backgroundExpectedBeforeClick = "rgb(255, 255, 255)";
        String backgroundExpectedAfterClick = "rgb(245, 251, 254)";

        assertTrue(loginPage.emailField.getCssValue("background").contains(backgroundExpectedBeforeClick));

        CustomActions.clickOnElement(driver, loginPage.emailField);
        assertTrue(wait.until(ExpectedConditions.attributeContains
                (loginPage.emailField, "background", backgroundExpectedAfterClick)));
    }

    @Epic("UI testing")
    @Feature("Authorization Page")
    @Story("Password field")
    @Test(description = "Check password field is displayed")
    @Description("Should check password field is displayed")
    public void shouldCheckThatPasswordFieldIsDisplayed() {
        AuthPage loginPage = new AuthPage(driver);
        String expected = "Пароль:";

        assertTrue(loginPage.passwordField.isDisplayed());
        assertEquals(loginPage.passwordField.getAccessibleName(), expected);
    }

    @Epic("UI testing")
    @Feature("Authorization Page")
    @Story("Password field")
    @Test(description = "Check password field has type \"password\"")
    @Description("Should check that password field has type \"password\"")
    public void shouldCheckThatPasswordHasTypePassword() {
        AuthPage loginPage = new AuthPage(driver);
        String expected = "password";

        assertEquals(loginPage.passwordField.getAttribute("type"), expected);
    }

    @Epic("UI testing")
    @Feature("Authorization Page")
    @Story("Password field")
    @Test(description = "Check password field changes color on click")
    @Description("Should check that password field changes color on click")
    public void shouldCheckPasswordFieldChangesColorOnClick() {
        AuthPage loginPage = new AuthPage(driver);
        String backgroundExpectedBeforeClick = "rgb(255, 255, 255)";
        String backgroundExpectedAfterClick = "rgb(245, 251, 254)";

        assertTrue(loginPage.passwordField.getCssValue("background").contains(backgroundExpectedBeforeClick));

        CustomActions.clickOnElement(driver, loginPage.passwordField);

        assertTrue(wait.until(ExpectedConditions.attributeContains
                (loginPage.passwordField, "background", backgroundExpectedAfterClick)));
    }

    @Epic("UI testing")
    @Feature("Authorization Page")
    @Story("Submit button")
    @Test(description = "Check that submit button changes background color on hover")
    @Description("Should check that submit button changes background color on hover")
    public void shouldCheckThatSubmitButtonChangesBackgroundColor() {
        AuthPage loginPage = new AuthPage(driver);
        String backgroundExpectedColorBeforeHover = "rgba(0, 168, 230, 1)";
        String backgroundExpectedColorOnHover = "rgba(53, 179, 238, 1)";

        assertEquals(loginPage.submitButton.getCssValue("background-color"), backgroundExpectedColorBeforeHover);

        CustomActions.hoverOnElement(driver, loginPage.submitButton);

        assertTrue(wait.until(ExpectedConditions.attributeContains
                (loginPage.submitButton, "background-color", backgroundExpectedColorOnHover)));
    }

    @Epic("UI testing")
    @Feature("Authorization Page")
    @Story("Submit button")
    @Test(description = "Check submit button is displayed")
    @Description("Should check that submit button is displayed")
    public void shouldCheckThatSubmitButtonIsDisplayed() {
        AuthPage loginPage = new AuthPage(driver);

        String expected = "Вход";

        assertTrue(loginPage.submitButton.isDisplayed());
        assertEquals(loginPage.submitButton.getText(), expected);
    }

    @Epic("UI testing")
    @Feature("Authorization Page")
    @Story("Submit button")
    @Test(description = "Check submit button has type \"submit\"")
    @Description("Should check that submit button has type \"submit\"")
    public void shouldCheckThatSubmitButtonHasTypeSubmit() {
        AuthPage loginPage = new AuthPage(driver);
        String expected = "submit";

        assertEquals(loginPage.submitButton.getAttribute("type"), expected);
    }

    @Epic("Functional testing")
    @Feature("Authorization Page")
    @Story("Valid authorization")
    @Test(description = "Authorization check with valid email and password")
    @Description("Should check authorization with valid email and password")
    public void shouldCheckAuthorizationWithValidEmailAndPassword() {
        AuthPage loginPage = new AuthPage(driver);

        DataGenerator.User validUser = new DataGenerator().getValidUser();
        loginPage.enterEmail(validUser.getLogin());
        loginPage.enterPassword(validUser.getPassword());
        loginPage.clickOnSubmitButton();

        assertFalse(loginPage.authPage.isDisplayed());
    }

    @Epic("Functional testing")
    @Feature("Authorization Page")
    @Story("Invalid authorization")
    @Test(description = "Check alert message of incorrect email format")
    @Description("Should check for showing message about incorrect email format")
    public void shouldCheckForShowingMessageAboutIncorrectEmail() {
        AuthPage loginPage = new AuthPage(driver);

        String expected = "Неверный формат E-Mail";
        String validPassword = new DataGenerator().getValidUser().getPassword();
        String randomString = DataGenerator.generateString(10, "en");

        loginPage.enterEmail(randomString);
        loginPage.enterPassword(validPassword);
        loginPage.clickOnSubmitButton();

        assertTrue(loginPage.alertMessage.isDisplayed());
        assertEquals(loginPage.alertMessage.getText(), expected);
    }

    @Epic("Functional testing")
    @Feature("Authorization Page")
    @Story("Invalid authorization")
    @Test(description = "Check alert message of empty email")
    @Description("Should check for showing message about empty email")
    public void shouldCheckForShowingMessageAboutEmptyEmail() {
        AuthPage loginPage = new AuthPage(driver);

        String expected = "Неверный формат E-Mail";
        String validPassword = new DataGenerator().getValidUser().getPassword();

        loginPage.enterPassword(validPassword);
        loginPage.clickOnSubmitButton();

        assertTrue(loginPage.alertMessage.isDisplayed());
        assertEquals(loginPage.alertMessage.getText(), expected);
    }

    @Epic("Functional testing")
    @Feature("Authorization Page")
    @Story("Invalid authorization")
    @Test(description = "Check alert message of invalid email")
    @Description("Should check for showing message about invalid email")
    public void shouldCheckForShowingMessageAboutInvalidEmail() {
        AuthPage loginPage = new AuthPage(driver);

        String expected = "Неверный E-Mail или пароль";
        String validPassword = new DataGenerator().getValidUser().getPassword();
        String randomEmail = DataGenerator.generateEmail();

        loginPage.enterEmail(randomEmail);
        loginPage.enterPassword(validPassword);
        loginPage.clickOnSubmitButton();

        assertTrue(loginPage.alertMessage.isDisplayed());
        assertEquals(loginPage.alertMessage.getText(), expected);
    }

    @Epic("Functional testing")
    @Feature("Authorization Page")
    @Story("Invalid authorization")
    @Test(description = "Check alert message of invalid password")
    @Description("Should check for showing message about invalid password")
    public void shouldCheckForShowingMessageAboutInvalidPassword() {
        AuthPage loginPage = new AuthPage(driver);

        String expected = "Неверный E-Mail или пароль";
        String validEmail = new DataGenerator().getValidUser().getLogin();
        String randomString = DataGenerator.generateString(5, "en");

        loginPage.enterEmail(validEmail);
        loginPage.enterPassword(randomString);
        loginPage.clickOnSubmitButton();

        assertTrue(loginPage.alertMessage.isDisplayed());
        assertEquals(loginPage.alertMessage.getText(), expected);
    }

    @Epic("Functional testing")
    @Feature("Authorization Page")
    @Story("Invalid authorization")
    @Test(description = "Check alert message of empty password")
    @Description("Should check for showing message about empty password")
    public void shouldCheckForShowingMessageAboutEmptyPassword() {
        AuthPage loginPage = new AuthPage(driver);

        String expected = "Неверный E-Mail или пароль";
        String validEmail = new DataGenerator().getValidUser().getLogin();

        loginPage.enterEmail(validEmail);
        loginPage.clickOnSubmitButton();

        assertTrue(loginPage.alertMessage.isDisplayed());
        assertEquals(loginPage.alertMessage.getText(), expected);
    }

    @Epic("UI testing")
    @Feature("Authorization Page")
    @Story("Alert message")
    @Test(description = "Check that alert message is displayed")
    @Description("Should check for showing of alert message")
    public void shouldCheckForShowingOfAlertMessage() {
        AuthPage loginPage = new AuthPage(driver);

        loginPage.clickOnSubmitButton();

        assertTrue(loginPage.alertMessage.isDisplayed());
        assertTrue(loginPage.closeAlertMessageButton.isDisplayed());
    }

    @Epic("UI testing")
    @Feature("Authorization Page")
    @Story("Alert message")
    @Test(description = "Check that alert message is not displayed after closing")
    @Description("Should check that alert message is not displayed after closing")
    public void shouldCheckAlertMessageIsNotDisplayedAfterClosing() {
        AuthPage loginPage = new AuthPage(driver);

        loginPage.clickOnSubmitButton();

        wait.until(ExpectedConditions.elementToBeClickable(loginPage.closeAlertMessageButton));
        loginPage.clickOnCloseAlertMessageButton();

        assertTrue(wait.until(ExpectedConditions.invisibilityOf(loginPage.alertMessage)));
    }
    @Epic("Security testing")
    @Feature("Authorization Page")
    @Story("Injections Email field")
    @Test(description = "Show alert message when sql injecting email field")
    @Description("Should check show alert message when insert sql injection in email field")
    public void shouldCheckShowAlertMessageWhenInsertSqlInjectionInEmailField() {
        AuthPage loginPage = new AuthPage(driver);
        String sqlInjection = " 1' OR '1'='1";

        loginPage.enterEmail(sqlInjection);
        loginPage.enterPassword(new DataGenerator().getValidUser().getPassword());
        loginPage.clickOnSubmitButton();

        assertTrue(loginPage.alertMessage.isDisplayed());
        assertTrue(loginPage.authPage.isDisplayed());
    }

    @Epic("Security testing")
    @Feature("Authorization Page")
    @Story("Injections Email field")
    @Test(description = "Show alert message when xss injecting email field")
    @Description("Should check show alert message when insert xss injection in email field")
    public void shouldCheckShowAlertMessageWhenInsertXssInjectionInEmailField() {
        AuthPage loginPage = new AuthPage(driver);
        String xssInjection = "<iframe src=\"javascript:…\">";

        loginPage.enterEmail(xssInjection);
        loginPage.enterPassword(new DataGenerator().getValidUser().getPassword());
        loginPage.clickOnSubmitButton();

        assertTrue(loginPage.alertMessage.isDisplayed());
        assertTrue(loginPage.authPage.isDisplayed());
    }

    @Epic("Security testing")
    @Feature("Authorization Page")
    @Story("Injections Password field")
    @Test(description = "Show alert message when sql injecting password field")
    @Description("Should check show alert message when insert sql injection in password field")
    public void shouldCheckShowAlertMessageWhenInsertSqlInjectionInPasswordField() {
        AuthPage loginPage = new AuthPage(driver);
        String sqlInjection = " 1' OR '1'='1";

        loginPage.enterEmail(new DataGenerator().getValidUser().getLogin());
        loginPage.enterPassword(sqlInjection);
        loginPage.clickOnSubmitButton();

        assertTrue(loginPage.alertMessage.isDisplayed());
        assertTrue(loginPage.authPage.isDisplayed());
    }

    @Epic("Security testing")
    @Feature("Authorization Page")
    @Story("Injections Password field")
    @Test(description = "Show alert message when xss injecting password field")
    @Description("Should check show alert message when insert xss injection in password field")
    public void shouldCheckShowAlertMessageWhenInsertXssInjectionInPasswordField() {
        AuthPage loginPage = new AuthPage(driver);
        String xssInjection = "<iframe src=\"javascript:…\">";

        loginPage.enterEmail(new DataGenerator().getValidUser().getLogin());
        loginPage.enterPassword(xssInjection);
        loginPage.clickOnSubmitButton();

        assertTrue(loginPage.alertMessage.isDisplayed());
        assertTrue(loginPage.authPage.isDisplayed());
    }
}
