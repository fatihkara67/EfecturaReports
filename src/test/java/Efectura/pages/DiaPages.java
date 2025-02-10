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

    @FindBy(xpath = "//h2[contains(.,'No results match your search criteria')]")
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

    @FindBy(xpath = "//span[contains(text(),'Impersonate')]")
    private WebElement impersonateButton;

    @FindBy(xpath = "//a[@id='impersonate-fletum']")
    private WebElement impersonateFletumButton;

    //------------------------------------------------------------------------------
    @FindBy(xpath = "//span[contains(text(),'Efectura-QA')]")
    private WebElement efecturaMailGroup;

    @FindBy(xpath = "//span[contains(text(),'Deneme')]")
    private WebElement denemeMailGroup;

    @FindBy(xpath = "//div/div/div/div[3]/div/div/div/div/span[starts-with(text(),'QA Team')]")
    private WebElement qaMailGroup;

    @FindBy(xpath = "//span[contains(text(),'Gruplara Git')]")
    private WebElement goToGroupsButton;

    @FindBy(xpath = "//span[contains(text(),'E-posta gönder')]")
    private WebElement sendMailLink;

    @FindBy(xpath = "//p[contains(text(),'couldn't log you in')]")
    private static WebElement couldNotLoginText;
    //-----------------------------------------------------------------------------------

    public WebElement getCouldNotLoginText() {
        return couldNotLoginText;
    }

    public void loginElastic() {
        Driver.getDriver().get(ConfigurationReader.getProperty("bpm"));
        BrowserUtils.waitForVisibility(usernameInputBox,50);
        usernameInputBox.sendKeys(ConfigurationReader.getProperty("eUsername"));
        passwordInputBox.sendKeys(ConfigurationReader.getProperty("ePassword"));
        loginButton.click();
        BrowserUtils.wait(2);
    }

    List<String> results = new ArrayList<>();
    public void verify(String service) {
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
        emailInputBox.sendKeys("fkara1667@gmail.com");
        nextButton.click();
        BrowserUtils.waitForVisibility(emailPasswordInputBox,5);
        emailPasswordInputBox.sendKeys("5355159Fk@");
        nextButton.click();
        BrowserUtils.waitForVisibility(composeButton,5);
    }

    public void clickComposeButton() {composeButton.click();}

    public void enterEmailCredentials() {
        BrowserUtils.wait(3);
        recipientsInputBox.sendKeys("fatih.kara@efectura.com");
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
        fletumUsernameInputBox.sendKeys("fatihkara");
        fletumPasswordInputBox.sendKeys("Efectura123");
        fletumSubmitButton.click();
    }

    public void searchFor(String searchFlow) {
        searchFilterFlow.sendKeys(searchFlow);
        BrowserUtils.wait(4);
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
        fletumUsernameInputBox.sendKeys("Tedarikçi");
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

    public void enterRecipients() {
        List<String> emailRecipients = new ArrayList<>();
        emailRecipients.add("fatih.kara@efectura.com");
        emailRecipients.add("emre.kurt@efectura.com");
        emailRecipients.add("cem@efectura.com");
        emailRecipients.add("adem.ciftci@efectura.com");
        emailRecipients.add("cagdas.bakin@efectura.com");
        emailRecipients.add("onur.coskun@efectura.com");

        for (String recipient : emailRecipients) {
//            outlookRecipientsInputBox.click();
            outlookRecipientsInputBox.sendKeys(recipient);
            outlookRecipientsInputBox.sendKeys(", ");
            BrowserUtils.wait(2);
        }

    }

    public void sendMailForDia() {
        List<String> emailRecipients = new ArrayList<>();
        emailRecipients.add("ihsan.dinc@efectura.com");
        emailRecipients.add("semanur.gozuacik@efectura.com");
//        emailRecipients.add("cem@efectura.com");
//        emailRecipients.add("adem.ciftci@efectura.com");
//        emailRecipients.add("cagdas.bakin@efectura.com");
//        emailRecipients.add("onur.coskun@efectura.com");
        emailRecipients.add("beyza.onal@efectura.com");
        emailRecipients.add("fatih.kara@efectura.com");


        for (String recipient : emailRecipients) {
            BrowserUtils.wait(12);
            outlookNewMailButton.click();
            BrowserUtils.waitForVisibility(outlookRecipientsInputBox,30);
            outlookRecipientsInputBox.sendKeys(recipient);
            outlookMailSubjectInputBox.sendKeys("Environment Elastic, Flows And Tedarik Screen Control");
            outlookMailMessageBodyInputBox.sendKeys(getEmailMessageBody());
            outlookMailMessageBodyInputBox.sendKeys("---------------------------------------------\n");
            outlookMailMessageBodyInputBox.sendKeys(getEmailMessageBodyForFlow());
            BrowserUtils.waitForClickability(outlookEmailSendButton,30);
            outlookEmailSendButton.click();
            BrowserUtils.wait(2);
        }
    }


    //-------------------------------------------------------------------------
    public void sendGroupEmailForDia() {
//        efecturaMailGroup.click();
//        denemeMailGroup.click();
        goToGroupsButton.click();
        qaMailGroup.click();
        BrowserUtils.wait(1);
        sendMailLink.click();

        BrowserUtils.wait(5);
        outlookMailSubjectInputBox.sendKeys("Environment Elastic, Flows And Tedarik Screen Control");
        outlookMailMessageBodyInputBox.sendKeys(getEmailMessageBody());
        outlookMailMessageBodyInputBox.sendKeys("---------------------------------------------\n");
        outlookMailMessageBodyInputBox.sendKeys(getEmailMessageBodyForFlow());
        BrowserUtils.waitForClickability(outlookEmailSendButton,30);
        outlookEmailSendButton.click();
        BrowserUtils.wait(2);


    }

    public void impersonateBeyza() {
        driver.get("https://diageo.efectura.com/UserManage/Edit/57599cee-cc5b-4f93-acd8-e1fc1de0caab");
        BrowserUtils.wait(2);
        impersonateButton.click();
        BrowserUtils.wait(1);
        impersonateFletumButton.click();
    }

    public void sendMailForMM() {

        List<String> emailRecipients = new ArrayList<>();
        emailRecipients.add("ihsan.dinc@efectura.com");
//        emailRecipients.add("semanur.gozuacik@efectura.com");
//        emailRecipients.add("cem@efectura.com");
//        emailRecipients.add("adem.ciftci@efectura.com");
//        emailRecipients.add("cagdas.bakin@efectura.com");
//        emailRecipients.add("onur.coskun@efectura.com");
        emailRecipients.add("beyza.onal@efectura.com");
        emailRecipients.add("fatih.kara@efectura.com");


        for (String recipient : emailRecipients) {
            BrowserUtils.wait(12);
            outlookNewMailButton.click();
            BrowserUtils.waitForVisibility(outlookRecipientsInputBox,30);
            outlookRecipientsInputBox.sendKeys(recipient);
            outlookMailSubjectInputBox.sendKeys("MM Service Controls");
            outlookMailMessageBodyInputBox.sendKeys(getEmailMessageBody());
            BrowserUtils.waitForClickability(outlookEmailSendButton,30);
            outlookEmailSendButton.click();
            BrowserUtils.wait(2);
        }

    }

    public void sendGroupEmailForMM() {
//        denemeMailGroup.click();
        goToGroupsButton.click();
        qaMailGroup.click();
        BrowserUtils.wait(1);
        sendMailLink.click();


        BrowserUtils.wait(5);
        outlookMailSubjectInputBox.sendKeys("MM Service Controls");
        outlookMailMessageBodyInputBox.sendKeys(getEmailMessageBody());
        BrowserUtils.waitForClickability(outlookEmailSendButton,30);
        outlookEmailSendButton.click();
        BrowserUtils.wait(2);
    }
}
