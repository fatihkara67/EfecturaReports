package Efectura.pages;

import Efectura.utilities.BrowserUtils;
import Efectura.utilities.ConfigurationReader;
import Efectura.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

import static Efectura.utilities.BrowserUtils.isElementDisplayed;

public class SlkPages extends BasePage {

    @FindBy(id = "login_email")
    private WebElement emailNewRelic;

    @FindBy(id = "login_password")
    private WebElement passwordNewRelic;

    @FindBy(id = "login_submit")
    private WebElement submitNewRelic;

    @FindBy(xpath = "/html/body/div/div/div/div[3]/div[3]/div[2]/form")
    private WebElement endNewRelic;

    @FindBy(xpath = "//h2[contains(text(),'logs found')]")
    private WebElement errorInfoNewRelic;

    @FindBy(xpath = "//h4[text()='No logs found during this time range']")
    private WebElement noMatchingInfo;

    @FindBy(xpath = "//input[@type='email']")
    private WebElement outlookEmailInputBox;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement outlookSubmitButton;

    @FindBy(xpath = "//input[@type='password']")
    private WebElement outlookPasswordInputBox;

    @FindBy(xpath = "//span[text()='Yeni posta']")
    private WebElement outlookNewMailButton;

    @FindBy(xpath = "//div[@aria-label='Kime']")
    private WebElement outlookRecipientsInputBox;

    @FindBy(xpath = "(//span[text()='fatih.kara@efectura.com'])[1]")
    private WebElement karaMailOption;

    @FindBy(xpath = "(//span[text()='cem@efectura.com'])[1]")
    private WebElement cemMailOption;

    @FindBy(xpath = "(//span[text()='emre.kurt@efectura.com'])[1]")
    private WebElement emreMailOption;

    @FindBy(xpath = "//input[@placeholder='Konu ekleyin']")
    private WebElement outlookMailSubjectInputBox;

    @FindBy(xpath = "//div[@aria-label='İleti gövdesi, çıkmak için Alt+F10’a basın']")
    private WebElement outlookMailMessageBodyInputBox;

    @FindBy(xpath = "//button[@title='Gönder (Ctrl+Enter)']")
    private WebElement outlookEmailSendButton;

    @FindBy(xpath = "//*[@id='O365_MainLink_MePhoto']/div/div/div/div/div[2]")
    private WebElement outlookLogoutProfile;

    @FindBy(id = "mectrl_body_signOut")
    private WebElement outlookLogoutButton;


    public void loginNewRelic() {
        Driver.getDriver().get("https://one.eu.newrelic.com/nr1-core?account=4144276&state=d7d8a30e-bff2-8c4b-0472-eb7573a4d579");
        BrowserUtils.waitForVisibility(emailNewRelic, 25);
        emailNewRelic.sendKeys(ConfigurationReader.getProperty("newRelicUserName"));
        submitNewRelic.click();
        passwordNewRelic.sendKeys(ConfigurationReader.getProperty("newRelicPassword"));
        submitNewRelic.click();
        BrowserUtils.wait(5);
        endNewRelic.click();
        submitNewRelic.click();
        BrowserUtils.wait(5);
    }

    List<String> results = new ArrayList<>();
    public void verify(String service) {
        BrowserUtils.wait(5);
        if (!isElementDisplayed(noMatchingInfo)) {
            results.add(service + ": Error Number: " + errorInfoNewRelic.getText());
        } else {
            results.add(service + " : SUCCESS");
        }
    }

    List<String> resultsForRestarts = new ArrayList<>();
    public void verifyForRestarts(String restart) {
        BrowserUtils.wait(5);
        if (!isElementDisplayed(noMatchingInfo)) {
            results.add(restart + ": Error Number: " + errorInfoNewRelic.getText());
        } else {
            results.add(restart + " : SUCCESS");
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

    public String getEmailMessageBodyForRestarts() {
        StringBuilder emailBody = new StringBuilder();
        for (String result : resultsForRestarts) {
            emailBody.append(result).append("\n");
        }
        System.out.println(emailBody);
        return emailBody.toString();
    }

    public void loginOutlook() {
        Driver.getDriver().get("https://outlook.live.com/mail/0/?nlp=1");
        outlookEmailInputBox.sendKeys("efectura.report@gmail.com");
        outlookSubmitButton.click();
        outlookPasswordInputBox.sendKeys("efectura1667");
        outlookSubmitButton.click();
        outlookSubmitButton.click();
        BrowserUtils.wait(7);
    }

    public void sendMailForSlk() {
        List<String> emailRecipients = new ArrayList<>();
        emailRecipients.add("fatih.kara@efectura.com");
        emailRecipients.add("emre.kurt@efectura.com");
        emailRecipients.add("cem@efectura.com");
        emailRecipients.add("adem.ciftci@efectura.com");
        emailRecipients.add("cagdas.bakin@efectura.com");
        emailRecipients.add("onur.coskun@efectura.com");
        emailRecipients.add("semanur.gozuacik@efectura.com");

        for (String recipient : emailRecipients) {
            outlookNewMailButton.click();
            BrowserUtils.waitForVisibility(outlookRecipientsInputBox,30);
            outlookRecipientsInputBox.sendKeys(recipient);
            outlookMailSubjectInputBox.sendKeys("SLK Environment Controls");
            outlookMailMessageBodyInputBox.sendKeys(getEmailMessageBody());
            outlookMailMessageBodyInputBox.sendKeys("------------------------------------\n");
            outlookMailMessageBodyInputBox.sendKeys(getEmailMessageBodyForRestarts());
            outlookEmailSendButton.click();
            BrowserUtils.wait(2);
        }
    }

    public void logoutMail() {
        outlookLogoutProfile.click();
        BrowserUtils.wait(5);
        outlookLogoutButton.click();
        BrowserUtils.wait(5);
    }

}
