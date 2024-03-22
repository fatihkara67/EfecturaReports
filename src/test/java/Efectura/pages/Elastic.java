package Efectura.pages;

import Efectura.utilities.BrowserUtils;
import Efectura.utilities.ConfigurationReader;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static Efectura.utilities.BrowserUtils.isElementDisplayed;

public class Elastic extends BasePage {

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

    public void loginElastic() {
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
        emailInputBox.sendKeys("fatih.kara@efectura.com");
        nextButton.click();
        BrowserUtils.waitForVisibility(emailPasswordInputBox,5);
        emailPasswordInputBox.sendKeys("5355159Fk@");
        nextButton.click();
        BrowserUtils.waitForVisibility(composeButton,5);
    }

    public void clickComposeButton() {composeButton.click();}

    public void enterEmailCredentials() {
        BrowserUtils.wait(3);
        recipientsInputBox.sendKeys("fkara1667@gmail.com, emre.kurt@efectura.com");
        subjectInputBox.sendKeys("Environment Controls");
        System.out.println("Results: ");
        System.out.println(getEmailMessageBody());
        messageBodyInputBox.sendKeys(getEmailMessageBody());
    }

    public void clickSendButton() {
        sendButton.click();
        BrowserUtils.waitForVisibility(messageSentInfo,5);
    }
}
