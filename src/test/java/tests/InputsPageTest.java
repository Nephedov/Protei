package tests;

import actions.CustomActions;
import actions.DriverConfig;
import data.DataGenerator;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.AuthPage;
import pages.InputsPage;

import static org.testng.Assert.*;

public class InputsPageTest {
    public WebDriver driver;

    @BeforeMethod(description = "Open html with authorization")
    public void setup() {
        driver = new DriverConfig().setUpDriverChrome();
        new AuthPage(driver).validAuthorization();
    }

    @AfterMethod(description = "Close html")
    public void tearDown() {
        driver.quit();
        driver = null;
    }

    @Epic("Functional testing")
    @Feature("Inputs Page")
    @Story("Valid inputs")
    @Test(description = "Valid inputs with one selected checkbox and first radiobutton")
    @Description("Should check that valid data with one selected checkbox and first radiobutton added to table")
    public void shouldCheckThatDataWithOneCheckboxAddedToTable() throws InterruptedException {
        InputsPage inputsPage = new InputsPage(driver);
        DataGenerator.generateEmail();
        String email = DataGenerator.generateEmail();
        String name = DataGenerator.generateString(5, "en");
        String gender = "Мужской";
        String expectedCheckbox = "1.1";
        String expectedRadiobutton = "2.1";

        inputsPage.fillTheFields(email, name, gender);
        inputsPage.clickOnCheckbox1();
        inputsPage.clickOnRadiobutton1();
        inputsPage.clickOnSubmitButton();
        Thread.sleep(2000L);
        inputsPage.clickOnCloseModalContentButton();

        assertEquals(inputsPage.getLastAddedValue("Email"), email);
        assertEquals(inputsPage.getLastAddedValue("Name"), name);
        assertEquals(inputsPage.getLastAddedValue("Gender"), gender);
        assertTrue(inputsPage.getLastAddedValue("Choice1").contains(expectedCheckbox));
        assertTrue(inputsPage.getLastAddedValue("Choice2").contains(expectedRadiobutton));
    }

    @Epic("Functional testing")
    @Feature("Inputs Page")
    @Story("Valid inputs")
    @Test(description = "Valid inputs with two selected checkboxes and second radiobutton")
    @Description("Should check that valid data with two selected checkboxes and second radiobutton added to table")
    public void shouldCheckThatDataWithTwoCheckboxesAddedToTable() throws InterruptedException {
        InputsPage inputsPage = new InputsPage(driver);
        DataGenerator.generateEmail();
        String email = DataGenerator.generateEmail();
        String name = DataGenerator.generateString(5, "en");
        String gender = "Женский";
        String expectedCheckbox = "1.1, 1.2";
        String expectedRadiobutton = "2.2";

        inputsPage.fillTheFields(email, name, gender);
        inputsPage.clickOnCheckbox1();
        inputsPage.clickOnCheckbox2();
        inputsPage.clickOnRadiobutton2();
        inputsPage.clickOnSubmitButton();
        Thread.sleep(2000L);
        inputsPage.clickOnCloseModalContentButton();

        assertEquals(inputsPage.getLastAddedValue("Email"), email);
        assertEquals(inputsPage.getLastAddedValue("Name"), name);
        assertEquals(inputsPage.getLastAddedValue("Gender"), gender);
        assertTrue(inputsPage.getLastAddedValue("Choice1").contains(expectedCheckbox));
        assertTrue(inputsPage.getLastAddedValue("Choice2").contains(expectedRadiobutton));
    }

    @Epic("Functional testing")
    @Feature("Inputs Page")
    @Story("Valid inputs")
    @Test(description = "Valid inputs without selected checkboxes with selected third radiobutton")
    @Description("Should check that valid data added to table without selected checkboxes with selected third radiobutton")
    public void shouldCheckThatDataWithoutCheckboxesAddedToTable() throws InterruptedException {
        InputsPage inputsPage = new InputsPage(driver);
        DataGenerator.generateEmail();
        String email = DataGenerator.generateEmail();
        String name = DataGenerator.generateString(5, "en");
        String gender = "Мужской";
        String expectedCheckbox = "Нет";
        String expectedRadiobutton = "2.3";

        inputsPage.fillTheFields(email, name, gender);
        inputsPage.clickOnRadiobutton3();
        inputsPage.clickOnSubmitButton();
        Thread.sleep(2000L);
        inputsPage.clickOnCloseModalContentButton();

        assertEquals(inputsPage.getLastAddedValue("Email"), email);
        assertEquals(inputsPage.getLastAddedValue("Name"), name);
        assertEquals(inputsPage.getLastAddedValue("Gender"), gender);
        assertTrue(inputsPage.getLastAddedValue("Choice1").contains(expectedCheckbox));
        assertTrue(inputsPage.getLastAddedValue("Choice2").contains(expectedRadiobutton));
    }

    @Epic("Functional testing")
    @Feature("Inputs Page")
    @Story("Valid inputs")
    @Test(description = "Valid inputs without selected radiobutton and checkboxes")
    @Description("Should check that valid data added to table without selected radiobutton and checkboxes")
    public void shouldCheckThatDataWithoutRadiobuttonAddedToTable() throws InterruptedException {
        InputsPage inputsPage = new InputsPage(driver);
        DataGenerator.generateEmail();
        String email = DataGenerator.generateEmail();
        String name = DataGenerator.generateString(5, "en");
        String gender = "Мужской";
        String expectedCheckbox = "Нет";
        String expectedRadiobutton = "";

        inputsPage.fillTheFields(email, name, gender);
        inputsPage.clickOnSubmitButton();
        Thread.sleep(2000L);
        inputsPage.clickOnCloseModalContentButton();

        assertEquals(inputsPage.getLastAddedValue("Email"), email);
        assertEquals(inputsPage.getLastAddedValue("Name"), name);
        assertEquals(inputsPage.getLastAddedValue("Gender"), gender);
        assertTrue(inputsPage.getLastAddedValue("Choice1").contains(expectedCheckbox));
        assertEquals(inputsPage.getLastAddedValue("Choice2"), expectedRadiobutton);
    }

    @Epic("Functional testing")
    @Feature("Inputs Page")
    @Story("Invalid inputs")
    @Test(description = "Check alert message of incorrect email format")
    @Description("Should check for showing message about incorrect email format")
    public void shouldCheckForShowingAlertMessageAboutIncorrectEmailFormat() {
        InputsPage inputsPage = new InputsPage(driver);
        String email = DataGenerator.generateString(10, "en");
        String name = DataGenerator.generateString(5, "en");
        String gender = "Мужской";
        String expected = "Неверный формат E-Mail";

        inputsPage.fillTheFields(email, name, gender);
        inputsPage.clickOnSubmitButton();

        assertTrue(inputsPage.alertMessage.isDisplayed());
        assertEquals(inputsPage.alertMessage.getText(), expected);
    }

    @Epic("Functional testing")
    @Feature("Inputs Page")
    @Story("Invalid inputs")
    @Test(description = "Check alert message of empty email")
    @Description("Should check for showing message about empty email")
    public void shouldCheckForShowingAlertMessageAboutEmptyEmail() {
        InputsPage inputsPage = new InputsPage(driver);
        String email = "";
        String name = DataGenerator.generateString(5, "en");
        String gender = "Мужской";
        String expected = "Неверный формат E-Mail";

        inputsPage.fillTheFields(email, name, gender);
        inputsPage.clickOnSubmitButton();

        assertTrue(inputsPage.alertMessage.isDisplayed());
        assertEquals(inputsPage.alertMessage.getText(), expected);
    }

    @Epic("Functional testing")
    @Feature("Inputs Page")
    @Story("Invalid inputs")
    @Test(description = "Check alert message of empty name")
    @Description("Should check for showing message about empty name")
    public void shouldCheckForShowingAlertMessageAboutEmptyName() {
        InputsPage inputsPage = new InputsPage(driver);
        String email = DataGenerator.generateEmail();
        String name = "";
        String gender = "Мужской";
        String expected = "Поле имя не может быть пустым";

        inputsPage.fillTheFields(email, name, gender);
        inputsPage.clickOnSubmitButton();

        assertTrue(inputsPage.alertMessage.isDisplayed());
        assertEquals(inputsPage.alertMessage.getText(), expected);
    }

    @Epic("UI testing")
    @Feature("Inputs Page")
    @Story("Email field")
    @Test(description = "Check that email field is displayed")
    @Description("Should check that email field is displayed")
    public void shouldCheckThatEmailFieldIsDisplayed() {
        InputsPage inputsPage = new InputsPage(driver);
        String expected = "E-Mail:";

        assertTrue(inputsPage.emailField.isDisplayed());
        assertEquals(inputsPage.emailField.getAccessibleName(), expected);
    }

    @Epic("UI testing")
    @Feature("Inputs Page")
    @Story("Email field")
    @Test(description = "Check email field changes color on click")
    @Description("Should check that email field changes color on click")
    public void shouldCheckEmailFieldChangesColorOnClick() throws InterruptedException {
        InputsPage inputsPage = new InputsPage(driver);
        String backgroundBeforeClick = "rgb(255, 255, 255)";
        String backgroundAfterClick = "rgb(245, 251, 254)";

        assertTrue(inputsPage.emailField.getCssValue("background").contains(backgroundBeforeClick));
        CustomActions.clickOnElement(driver, inputsPage.emailField);
        // wait should be added here
        Thread.sleep(2000L);
        assertTrue(inputsPage.emailField.getCssValue("background").contains(backgroundAfterClick));
    }

    @Epic("UI testing")
    @Feature("Inputs Page")
    @Story("Name field")
    @Test(description = "Check that name field is displayed")
    @Description("Should check that name field is displayed")
    public void shouldCheckThatNameFieldIsDisplayed() {
        InputsPage inputsPage = new InputsPage(driver);
        String expected = "Имя:";

        assertTrue(inputsPage.nameField.isDisplayed());
        assertEquals(inputsPage.nameField.getAccessibleName(), expected);
    }

    @Epic("UI testing")
    @Feature("Inputs Page")
    @Story("Name field")
    @Test(description = "Check name field changes color on click")
    @Description("Should check that name field changes color on click")
    public void shouldCheckNameFieldChangesColorOnClick() throws InterruptedException {
        InputsPage inputsPage = new InputsPage(driver);
        String backgroundBeforeClick = "rgb(255, 255, 255)";
        String backgroundAfterClick = "rgb(245, 251, 254)";

        assertTrue(inputsPage.nameField.getCssValue("background").contains(backgroundBeforeClick));
        CustomActions.clickOnElement(driver, inputsPage.nameField);
        // wait should be added here
        Thread.sleep(2000L);
        assertTrue(inputsPage.nameField.getCssValue("background").contains(backgroundAfterClick));
    }

    @Epic("UI testing")
    @Feature("Inputs Page")
    @Story("Gender field")
    @Test(description = "Check gender field is displayed")
    @Description("Should check that gender field is displayed")
    public void shouldCheckGenderFieldIsDisplayed() {
        InputsPage inputsPage = new InputsPage(driver);

        assertTrue(inputsPage.genderField.isDisplayed());
    }

    @Epic("UI testing")
    @Feature("Inputs Page")
    @Story("Gender field")
    @Test(description = "Check gender field has type \"select\"")
    @Description("Should check that gender field has type \"select\"")
    public void shouldCheckGenderFieldHasTypeSelect() {
        InputsPage inputsPage = new InputsPage(driver);
        String expected = "select";

        assertEquals(inputsPage.genderField.getTagName(), expected);
    }

    @Epic("UI testing")
    @Feature("Inputs Page")
    @Story("Gender field")
    @Test(description = "Check gender field options displayed")
    @Description("Should check that gender field options displayed")
    public void shouldCheckGenderFieldOptionsDisplayed() {
        InputsPage inputsPage = new InputsPage(driver);
        String genderMale = "Мужской";
        String genderFemale = "Женский";

        assertTrue(inputsPage.findGender(genderMale).isSelected());
        assertFalse(inputsPage.findGender(genderFemale).isSelected());
    }

    @Epic("UI testing")
    @Feature("Inputs Page")
    @Story("Alert message")
    @Test(description = "Check that alert message is displayed")
    @Description("Should check for showing of alert message")
    public void shouldCheckForShowingOfAlertMessage() {
        InputsPage inputsPage = new InputsPage(driver);

        inputsPage.clickOnSubmitButton();

        assertTrue(inputsPage.alertMessage.isDisplayed());
        assertTrue(inputsPage.closeAlertMessageButton.isDisplayed());
    }

    @Epic("UI testing")
    @Feature("Inputs Page")
    @Story("Alert message")
    @Test(description = "Check that alert message is not displayed after closing")
    @Description("Should check that alert message is not displayed after closing")
    public void shouldCheckAlertMessageIsNotDisplayedAfterClosing() throws InterruptedException {
        InputsPage inputsPage = new InputsPage(driver);

        inputsPage.clickOnSubmitButton();
        assertTrue(inputsPage.alertMessage.isDisplayed());
        inputsPage.clickOnCloseAlertMessageButton();

        Thread.sleep(2000L);
        assertFalse(CustomActions.isExist(driver, By.cssSelector("#authAlertsHolder>div")));
    }

    @Epic("UI testing")
    @Feature("Inputs Page")
    @Story("Submit button")
    @Test(description = "Check that submit button changes background color on hover")
    @Description("Should check that submit button changes background color on hover")
    public void shouldCheckThatSubmitButtonChangesBackgroundColor() {
        InputsPage inputsPage = new InputsPage(driver);
        String backgroundColorBeforeHover = "rgba(0, 168, 230, 1)";
        String backgroundColorOnHover = "rgba(53, 179, 238, 1)";

        assertEquals(inputsPage.submitButton.getCssValue("background-color"), backgroundColorBeforeHover);
        CustomActions.hoverOnElement(driver, inputsPage.submitButton);
        assertEquals(inputsPage.submitButton.getCssValue("background-color"), backgroundColorOnHover);
    }

    @Epic("UI testing")
    @Feature("Inputs Page")
    @Story("Submit button")
    @Test(description = "Check submit button is displayed")
    @Description("Should check that submit button is displayed")
    public void shouldCheckThatSubmitButtonIsDisplayed() {
        InputsPage inputsPage = new InputsPage(driver);

        String expected = "Добавить";

        assertTrue(inputsPage.submitButton.isDisplayed());
        assertEquals(inputsPage.submitButton.getText(), expected);
    }

    @Epic("UI testing")
    @Feature("Inputs Page")
    @Story("Submit button")
    @Test(description = "Check submit button has type \"submit\"")
    @Description("Should check that submit button has type \"submit\"")
    public void shouldCheckThatSubmitButtonHasTypeSubmit() {
        InputsPage inputsPage = new InputsPage(driver);
        String expected = "submit";

        assertEquals(inputsPage.submitButton.getAttribute("type"), expected);
    }

    @Epic("UI testing")
    @Feature("Inputs Page")
    @Story("Modal dialog")
    @Test(description = "Check the modal is displayed")
    @Description("Should check the display of the modal")
    public void shouldCheckTheDisplayOfTheModal() throws InterruptedException {
        InputsPage inputsPage = new InputsPage(driver);
        DataGenerator.generateEmail();
        String email = DataGenerator.generateEmail();
        String name = DataGenerator.generateString(5, "en");
        String gender = "Мужской";

        String expectedContent = "Данные добавлены.";
        String expectedClose = "Ok";

        inputsPage.fillTheFields(email, name, gender);
        inputsPage.clickOnSubmitButton();
        Thread.sleep(1000L);

        assertTrue(inputsPage.modalContent.isDisplayed());
        assertTrue(inputsPage.modalContent.getText().contains(expectedContent));
        assertTrue(inputsPage.closeModalContentButton.isDisplayed());
        assertTrue(inputsPage.closeModalContentButton.getText().contains(expectedClose));
    }

    @Epic("UI testing")
    @Feature("Inputs Page")
    @Story("Modal dialog")
    @Test(description = "Check that modal dialog is not displayed after confirm")
    @Description("Should check that modal dialog is not displayed after confirm")
    public void shouldCheckModalDialogIsNotDisplayedAfterConfirm() throws InterruptedException {
        InputsPage inputsPage = new InputsPage(driver);
        DataGenerator.generateEmail();
        String email = DataGenerator.generateEmail();
        String name = DataGenerator.generateString(5, "en");
        String gender = "Мужской";

        inputsPage.fillTheFields(email, name, gender);
        inputsPage.clickOnSubmitButton();
        Thread.sleep(1000L);
        assertTrue(inputsPage.modalContent.isDisplayed());
        inputsPage.clickOnCloseModalContentButton();

        assertFalse(CustomActions.isExist(driver, By.cssSelector("#authAlertsHolder>div")));
    }

    @Epic("UI testing")
    @Feature("Inputs Page")
    @Story("Table")
    @Test(description = "Check that the column headings is displayed")
    @Description("Should check that the column headings is displayed")
    public void shouldCheckThatTheColumnHeadingsIsDisplayed() {
        InputsPage inputsPage = new InputsPage(driver);

        assertTrue(inputsPage.getColumnTitle("Email").isDisplayed());
        assertTrue(inputsPage.getColumnTitle("Name").isDisplayed());
        assertTrue(inputsPage.getColumnTitle("Gender").isDisplayed());
        assertTrue(inputsPage.getColumnTitle("Choice1").isDisplayed());
        assertTrue(inputsPage.getColumnTitle("Choice2").isDisplayed());
    }

    @Epic("UI testing")
    @Feature("Inputs Page")
    @Story("Table")
    @Test(description = "Check that the column headings have values")
    @Description("Should check that the column headings have values")
    public void shouldCheckThatTheColumnHeadingsHaveValues() {
        InputsPage inputsPage = new InputsPage(driver);
        String expectedEmail = "E-Mail";
        String expectedName = "Имя";
        String expectedGender = "Пол";
        String expectedChoice1 = "Выбор 1";
        String expectedChoice2 = "Выбор 2";

        assertTrue(inputsPage.getColumnTitle("Email").getText().contains(expectedEmail));
        assertTrue(inputsPage.getColumnTitle("Name").getText().contains(expectedName));
        assertTrue(inputsPage.getColumnTitle("Gender").getText().contains(expectedGender));
        assertTrue(inputsPage.getColumnTitle("Choice1").getText().contains(expectedChoice1));
        assertTrue(inputsPage.getColumnTitle("Choice2").getText().contains(expectedChoice2));
    }

    @Epic("UI testing")
    @Feature("Inputs Page")
    @Story("Checkboxes")
    @Test(description = "Check that the checkbox 1 is displayed")
    @Description("Should check that the checkbox 1 is displayed")
    public void shouldCheckThatCheckbox1IsDisplayed() {
        InputsPage inputsPage = new InputsPage(driver);

        assertTrue(inputsPage.checkbox1.isDisplayed());
    }

    @Epic("UI testing")
    @Feature("Inputs Page")
    @Story("Checkboxes")
    @Test(description = "Check that the checkbox 1 has type \"checkbox\"")
    @Description("Should check that the checkbox 1 has type \"checkbox\"")
    public void shouldCheckThatCheckboxHas1TypeCheckbox() {
        InputsPage inputsPage = new InputsPage(driver);
        String expected = "checkbox";

        assertEquals(inputsPage.checkbox1.getAttribute("type"), expected);
    }

    @Epic("UI testing")
    @Feature("Inputs Page")
    @Story("Checkboxes")
    @Test(description = "Check that the checkbox 1 is selected")
    @Description("Should check that the checkbox 1 is selected")
    public void shouldCheckThatCheckbox1IsSelected() {
        InputsPage inputsPage = new InputsPage(driver);

        assertFalse(inputsPage.checkbox1.isSelected());
        inputsPage.clickOnCheckbox1();
        assertTrue(inputsPage.checkbox1.isSelected());
    }

    @Epic("UI testing")
    @Feature("Inputs Page")
    @Story("Checkboxes")
    @Test(description = "Check that the checkbox 2 is displayed")
    @Description("Should check that the checkbox 2 is displayed")
    public void shouldCheckThatCheckbox2IsDisplayed() {
        InputsPage inputsPage = new InputsPage(driver);

        assertTrue(inputsPage.checkbox2.isDisplayed());
    }

    @Epic("UI testing")
    @Feature("Inputs Page")
    @Story("Checkboxes")
    @Test(description = "Check that the checkbox 2 has type \"checkbox\"")
    @Description("Should check that the checkbox 2 has type \"checkbox\"")
    public void shouldCheckThatCheckbox2HasTypeCheckbox() {
        InputsPage inputsPage = new InputsPage(driver);
        String expected = "checkbox";

        assertEquals(inputsPage.checkbox2.getAttribute("type"), expected);
    }

    @Epic("UI testing")
    @Feature("Inputs Page")
    @Story("Checkboxes")
    @Test(description = "Check that the checkbox 2 is selected")
    @Description("Should check that the checkbox 2 is selected")
    public void shouldCheckThatCheckbox2IsSelected() {
        InputsPage inputsPage = new InputsPage(driver);

        assertFalse(inputsPage.checkbox2.isSelected());
        inputsPage.clickOnCheckbox2();
        assertTrue(inputsPage.checkbox2.isSelected());
    }

    @Epic("UI testing")
    @Feature("Inputs Page")
    @Story("Radio buttons")
    @Test(description = "Check that the radiobutton 1 is displayed")
    @Description("Should check that the radiobutton 1 is displayed")
    public void shouldCheckThatRadiobutton1IsDisplayed() {
        InputsPage inputsPage = new InputsPage(driver);

        assertTrue(inputsPage.radiobutton1.isDisplayed());
    }

    @Epic("UI testing")
    @Feature("Inputs Page")
    @Story("Radio buttons")
    @Test(description = "Check that the radiobutton 1 has type \"radio\"")
    @Description("Should check that the radiobutton 1 has type \"radio\"")
    public void shouldCheckThatRadiobutton1HasTypeRadio() {
        InputsPage inputsPage = new InputsPage(driver);
        String expected = "radio";

        assertEquals(inputsPage.radiobutton1.getAttribute("type"), expected);
    }

    @Epic("UI testing")
    @Feature("Inputs Page")
    @Story("Radio buttons")
    @Test(description = "Check that the radiobutton 1 is selected")
    @Description("Should check that the radiobutton 1 is selected")
    public void shouldCheckThatRadiobutton1IsSelected() {
        InputsPage inputsPage = new InputsPage(driver);

        assertFalse(inputsPage.radiobutton1.isSelected());
        inputsPage.clickOnRadiobutton1();
        assertTrue(inputsPage.radiobutton1.isSelected());
    }

    @Epic("UI testing")
    @Feature("Inputs Page")
    @Story("Radio buttons")
    @Test(description = "Check that the radiobutton 2 is displayed")
    @Description("Should check that the radiobutton 2 is displayed")
    public void shouldCheckThatRadiobutton2IsDisplayed() {
        InputsPage inputsPage = new InputsPage(driver);

        assertTrue(inputsPage.radiobutton2.isDisplayed());
    }

    @Epic("UI testing")
    @Feature("Inputs Page")
    @Story("Radio buttons")
    @Test(description = "Check that the radiobutton 2 has type \"radio\"")
    @Description("Should check that the radiobutton 2 has type \"radio\"")
    public void shouldCheckThatRadiobutton2HasTypeRadio() {
        InputsPage inputsPage = new InputsPage(driver);
        String expected = "radio";

        assertEquals(inputsPage.radiobutton2.getAttribute("type"), expected);
    }

    @Epic("UI testing")
    @Feature("Inputs Page")
    @Story("Radio buttons")
    @Test(description = "Check that the radiobutton 2 is selected")
    @Description("Should check that the radiobutton 2 is selected")
    public void shouldCheckThatRadiobutton2IsSelected() {
        InputsPage inputsPage = new InputsPage(driver);

        assertFalse(inputsPage.radiobutton2.isSelected());
        inputsPage.clickOnRadiobutton2();
        assertTrue(inputsPage.radiobutton2.isSelected());
    }

    @Epic("UI testing")
    @Feature("Inputs Page")
    @Story("Radio buttons")
    @Test(description = "Check that the radiobutton 3 is displayed")
    @Description("Should check that the radiobutton 3 is displayed")
    public void shouldCheckThatRadiobutton3IsDisplayed() {
        InputsPage inputsPage = new InputsPage(driver);

        assertTrue(inputsPage.radiobutton3.isDisplayed());
    }

    @Epic("UI testing")
    @Feature("Inputs Page")
    @Story("Radio buttons")
    @Test(description = "Check that the radiobutton 3 has type \"radio\"")
    @Description("Should check that the radiobutton 3 has type \"radio\"")
    public void shouldCheckThatRadiobutton3HasTypeRadio() {
        InputsPage inputsPage = new InputsPage(driver);
        String expected = "radio";

        assertEquals(inputsPage.radiobutton3.getAttribute("type"), expected);
    }

    @Epic("UI testing")
    @Feature("Inputs Page")
    @Story("Radio buttons")
    @Test(description = "Check that the radiobutton 3 is selected")
    @Description("Should check that the radiobutton 3 is selected")
    public void shouldCheckThatRadiobutton3IsSelected() {
        InputsPage inputsPage = new InputsPage(driver);

        assertFalse(inputsPage.radiobutton3.isSelected());
        inputsPage.clickOnRadiobutton3();
        assertTrue(inputsPage.radiobutton3.isSelected());
    }

    @Epic("UI testing")
    @Feature("Inputs Page")
    @Story("Radio buttons")
    @Test(description = "Check that only one radiobutton is selected")
    @Description("Should check that only one radiobutton is selected")
    public void shouldCheckThatOnlyOneRadiobuttonIsSelected() {
        InputsPage inputsPage = new InputsPage(driver);

        inputsPage.clickOnRadiobutton1();
        inputsPage.clickOnCheckbox2();
        inputsPage.clickOnRadiobutton3();

        assertFalse(inputsPage.radiobutton1.isSelected());
        assertFalse(inputsPage.radiobutton2.isSelected());
        assertTrue(inputsPage.radiobutton3.isSelected());
    }

    @Epic("Security testing")
    @Feature("Inputs Page")
    @Story("Injections Email field")
    @Test(description = "Show alert message when sql injecting email field")
    @Description("Should check show alert message when insert sql injection in email field")
    public void shouldCheckShowAlertMessageWhenInsertSqlInjectionInEmailField() {
        InputsPage inputsPage = new InputsPage(driver);
        String sqlInjection = " 1' OR '1'='1";
        String name = DataGenerator.generateString(5, "en");
        String gender = "Мужской";

        inputsPage.fillTheFields(sqlInjection, name, gender);
        inputsPage.clickOnSubmitButton();

        assertTrue(inputsPage.alertMessage.isDisplayed());
    }

    @Epic("Security testing")
    @Feature("Inputs Page")
    @Story("Injections Email field")
    @Test(description = "Show alert message when xss injecting email field")
    @Description("Should check show alert message when insert xss injection in email field")
    public void shouldCheckShowAlertMessageWhenInsertXssInjectionInEmailField() {
        InputsPage inputsPage = new InputsPage(driver);
        String xssInjection = "<iframe src=\"javascript:…\">";

        String name = DataGenerator.generateString(5, "en");
        String gender = "Мужской";

        inputsPage.fillTheFields(xssInjection, name, gender);
        inputsPage.clickOnSubmitButton();

        assertTrue(inputsPage.alertMessage.isDisplayed());
    }

    @Epic("Security testing")
    @Feature("Inputs Page")
    @Story("Injections Name field")
    @Test(description = "Show entered values sql injecting name field")
    @Description("Should check show entered values when insert sql injection in name field")
    public void shouldCheckThatTheEnteredValuesAreDisplayedWhenInsertSqlInjectionInNameField()
            throws InterruptedException {
        InputsPage inputsPage = new InputsPage(driver);
        String sqlInjection = " 1' OR '1'='1";
        String email = DataGenerator.generateEmail();
        String gender = "Мужской";

        inputsPage.fillTheFields(email, sqlInjection, gender);
        inputsPage.clickOnSubmitButton();
        Thread.sleep(1000L);
        inputsPage.clickOnCloseModalContentButton();

        assertEquals(inputsPage.getLastAddedValue("Name"), sqlInjection.trim());
    }

    @Epic("Security testing")
    @Feature("Inputs Page")
    @Story("Injections Name field")
    @Test(expectedExceptions = AssertionError.class, description = "Show entered values xss injecting name field")
    @Description("Should check show entered values when insert xss injection in name field")
    public void shouldCheckThatTheEnteredValuesAreDisplayedWhenInsertXssInjectionInNameField()
            throws InterruptedException {
        InputsPage inputsPage = new InputsPage(driver);
        String xssInjection = "<iframe src=\"javascript:…\">";
        String email = DataGenerator.generateEmail();
        String gender = "Мужской";

        inputsPage.fillTheFields(email, xssInjection, gender);
        inputsPage.clickOnSubmitButton();
        Thread.sleep(1000L);
        inputsPage.clickOnCloseModalContentButton();

        assertEquals(inputsPage.getLastAddedValue("Name"), xssInjection.trim());
    }
}
