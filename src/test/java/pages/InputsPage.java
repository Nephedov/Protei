package pages;

import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class InputsPage {
    public WebDriver driver;
    public InputsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy(id = "dataEmail")
    public WebElement emailField;

    @FindBy(id = "dataName")
    public WebElement nameField;

    @FindBy(id = "dataGender")
    public WebElement genderField;

    @FindBy(id = "dataCheck11")
    public WebElement checkbox1;

    @FindBy(id = "dataCheck12")
    public WebElement checkbox2;

    @FindBy(id = "dataSelect21")
    public WebElement radiobutton1;

    @FindBy(id = "dataSelect22")
    public WebElement radiobutton2;

    @FindBy(id = "dataSelect23")
    public WebElement radiobutton3;

    @FindBy(id = "dataSend")
    public WebElement submitButton;

    @FindBy(css = ".uk-modal-content")
    public WebElement modalContent;
    @FindBy(css = ".uk-button.uk-modal-close")
    public WebElement closeModalContentButton;

    @FindBy(css = "#dataAlertsHolder>div")
    public WebElement alertMessage;

    @FindBy(css = ".uk-alert-close")
    public WebElement closeAlertMessageButton;

    public void clickOnCloseAlertMessageButton() {
        Allure.step("Click on CloseAlertMessageButton");
        closeAlertMessageButton.click();
    }

    public void fillTheFields(String email, String name, String gender) {
        enterEmail(email);
        enterName(name);
        selectGender(gender);
    }

    public void enterEmail(String email) {
        Allure.step("Enter the value \"" + email + "\" in the email field");
        emailField.clear();
        emailField.sendKeys(email);
    }

    public void enterName(String name) {
        Allure.step("Enter the value \"" + name + "\" in the email field");
        nameField.clear();
        nameField.sendKeys(name);
    }

    public void clickOnGenderField() {
        Allure.step("Click on gender field");
        genderField.click();
    }

    public WebElement findGender(String gender) {
        int optionNumber = 0;
        if (gender.equals("Мужской")) {
            optionNumber = 1;
        } else if (gender.equals("Женский")) {
            optionNumber = 2;
        }
        return driver.findElement(By.cssSelector("#dataGender option:nth-child(" + optionNumber + ")"));
    }
    public void selectGender(String gender) {
        clickOnGenderField();
        Allure.step("Select gender \"" + gender + " \"");
        findGender(gender).click();
    }

    public void clickOnCheckbox1() {
        Allure.step("Click on checkbox 1");
        checkbox1.click();
    }

    public void clickOnCheckbox2() {
        Allure.step("Click on checkbox 2");
        checkbox2.click();
    }

    public void clickOnRadiobutton1() {
        Allure.step("Click on radio button 1");
        radiobutton1.click();
    }

    public void clickOnRadiobutton2() {
        Allure.step("Click on radio button 2");
        radiobutton2.click();
    }

    public void clickOnRadiobutton3() {
        Allure.step("Click on radio button 3");
        radiobutton3.click();
    }

    public void clickOnSubmitButton() {
        Allure.step("Click on SubmitButton");
        submitButton.click();
    }

    public void clickOnCloseModalContentButton() {
        Allure.step("Click on close modal content button");
        closeModalContentButton.click();
    }

    public int getNumberOfRows() {
        if (driver.findElements(By.cssSelector("table>tbody>tr")).isEmpty()) {
            return 0;
        }
        else {
            return driver.findElements(By.cssSelector("table>tbody>tr")).toArray().length;
        }
    }

    public String getLastAddedValue(String columnName) {
        int indexElement = getNumberOfRows();
        int columnNumber = 0;

        if(columnName.equals("Email")) {
            columnNumber = 1;
        } else if (columnName.equals("Name")) {
            columnNumber = 2;
        } else if (columnName.equals("Gender")) {
            columnNumber = 3;
        } else if (columnName.equals("Choice1")) {
            columnNumber = 4;
        } else if (columnName.equals("Choice2")) {
            columnNumber = 5;
        } else {
            return null;
        }

        return driver.findElement(By.cssSelector("table>tbody tr:nth-child(" + indexElement + ") td:nth-child(" + columnNumber + ")")).getText();
    }

    public WebElement getColumnTitle(String columnName) {
        int columnNumber = 0;

        if(columnName.equals("Email")) {
            columnNumber = 1;
        } else if (columnName.equals("Name")) {
            columnNumber = 2;
        } else if (columnName.equals("Gender")) {
            columnNumber = 3;
        } else if (columnName.equals("Choice1")) {
            columnNumber = 4;
        } else if (columnName.equals("Choice2")) {
            columnNumber = 5;
        } else {
            return null;
        }
        return driver.findElement(By.cssSelector("table>thead th:nth-child("+ columnNumber +")"));
    }
}
