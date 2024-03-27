package Efectura.pages;

import Efectura.utilities.BrowserUtils;
import Efectura.utilities.ConfigurationReader;
import Efectura.utilities.Driver;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

import static Efectura.utilities.BrowserUtils.isElementDisplayed;

public class DiaPages extends BasePage {

    @FindBy(xpath = "//input[@name='username']")
    private WebElement usernameInputBox;

    @FindBy(xpath = "//input[@name='password']")
    private WebElement passwordInputBox;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement loginButton;

    @FindBy(xpath = "//h2[text()='No results match your search criteria']")
    private WebElement noMatchingInfo;

    @FindBy(xpath = "//div/div/strong")
    private WebElement numberOfFilteredData;

    @FindBy(xpath = "//input[@type='email']")
    private WebElement emailInputBox;

    @FindBy(xpath = "//input[@type='password']")
    private WebElement emailPasswordInputBox;

    @FindBy(xpath = "//span[text()='Sonraki']")
    private WebElement nextButton;

    @FindBy(xpath = "//div[text()='Compose']")
    private WebElement composeButton;

    @FindBy(xpath = "//input[@role='combobox']")
    private WebElement recipientsInputBox;

    @FindBy(xpath = "//input[@placeholder='Subject']")
    private WebElement subjectInputBox;

    @FindBy(xpath = "//div[@aria-label='Message Body']")
    private WebElement messageBodyInputBox;

    @FindBy(xpath = "//div[text()='Send']")
    private WebElement sendButton;

    @FindBy(xpath = "//span[text()='Message sent']")
    private WebElement messageSentInfo;

    @FindBy(id = "Username")
    private WebElement fletumUsernameInputBox;

    @FindBy(id = "password-field")
    private WebElement fletumPasswordInputBox;

    @FindBy(id = "submit")
    private WebElement fletumSubmitButton;

    @FindBy(id = "GeneralSearch")
    private WebElement searchFilterFlow;

    @FindBy(id = "taskList_table_info")
    private WebElement tableInfo;

    @FindBy(id = "taskList_table")
    private WebElement taskListTedarikci;


    public void loginElastic() {
        BrowserUtils.waitForVisibility(usernameInputBox,50);
        usernameInputBox.sendKeys(ConfigurationReader.getProperty("eUsername"));
        passwordInputBox.sendKeys(ConfigurationReader.getProperty("ePassword"));
        loginButton.click();
        BrowserUtils.wait(2);
    }

    List<String> results = new ArrayList<>();
    public void verify(String service) {
        BrowserUtils.wait(2);
        if (!isElementDisplayed(noMatchingInfo)) {
            results.add(service + ": Error Number: " + numberOfFilteredData.getText());
        } else {
            results.add(service + " : SUCCESS");
            Assert.assertTrue(noMatchingInfo.isDisplayed());
        }

    }

    public String getEmailMessageBody() {
        StringBuilder emailBody = new StringBuilder();
        for (String result : results) {
            emailBody.append(result).append("\n");
        }
        System.out.println(emailBody);
        return emailBody.toString();
    }

    public void loginEmail() {
        emailInputBox.sendKeys("emre.kurt@efectura.com");
        nextButton.click();
        BrowserUtils.waitForVisibility(emailPasswordInputBox,5);
        emailPasswordInputBox.sendKeys("efecturaemre");
        nextButton.click();
        BrowserUtils.waitForVisibility(composeButton,5);
    }

    public void clickComposeButton() {composeButton.click();}

    public void enterEmailCredentials() {
        BrowserUtils.wait(3);
        recipientsInputBox.sendKeys("fatih.kara@efectura.com, emre.kurt@efectura.com");
        subjectInputBox.sendKeys("Environment Elastic, Flows And Tedarik Screen Control");
        System.out.println("Results: ");
        System.out.println(getEmailMessageBody());
        System.out.println(getEmailMessageBodyForFlow());
        messageBodyInputBox.sendKeys(getEmailMessageBody());
        messageBodyInputBox.sendKeys(getEmailMessageBodyForFlow());
    }

    public void clickSendButton() {
        sendButton.click();
        BrowserUtils.waitForVisibility(messageSentInfo,5);
    }

    public void loginFletum() {
        fletumUsernameInputBox.sendKeys("Efectura");
        fletumPasswordInputBox.sendKeys("@3f3c1ur4");
        fletumSubmitButton.click();
    }

    public void searchFor(String searchFlow) {
        searchFilterFlow.sendKeys(searchFlow);
        BrowserUtils.wait(5);
    }

    String modulCount;
    public void takeModulCount() {
        modulCount = tableInfo.getText().split(" ")[0];
        System.out.println("Modül Sayısı: " + modulCount);
        searchFilterFlow.clear();
    }


    String menuCount;
    public void takeMenuCount() {
        menuCount = tableInfo.getText().split(" ")[0];
        System.out.println("Menü Sayısı: " + menuCount);
    }

    public void enterEmailCredentialsForFlow() {
        BrowserUtils.wait(3);
        recipientsInputBox.sendKeys("fkara1667@gmail.com, emre.kurt@efectura.com, cem@efectura.com");
        subjectInputBox.sendKeys("Fletum Flow Count And Tedarikçi Screen Control");
        System.out.println("Results: ");
        System.out.println(getEmailMessageBodyForFlow());
        messageBodyInputBox.sendKeys(getEmailMessageBodyForFlow());
    }

    private String getEmailMessageBodyForFlow() {
        return "Modül Sayısı: " + modulCount + "\n" + "Menü Sayısı: " + menuCount + "\n" + result;
    }

    public void tedarikciLogin() {
        Driver.getDriver().get("https://meygw.fletum.com/task/tasklistvendor");
        BrowserUtils.wait(5);
        fletumUsernameInputBox.sendKeys("tedarikçi");
        fletumPasswordInputBox.sendKeys("asdasd123");
        fletumSubmitButton.click();
    }

    String result;
    public void waitTedarikciTable() {
        BrowserUtils.waitForVisibility(taskListTedarikci, 30);
        if (taskListTedarikci.isDisplayed()){
            result = "Tedarikçi Ekran Kontrolü: OK";
        }else {
            result = "Tedarikçi Ekran Kontrolü: Tablo Gelmiyor";
        }
    }
}
