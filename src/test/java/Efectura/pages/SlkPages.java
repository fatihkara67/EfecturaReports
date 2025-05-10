package Efectura.pages;

import Efectura.utilities.BrowserUtils;
import Efectura.utilities.ConfigurationReader;
import Efectura.utilities.Driver;
import Efectura.utilities.Pages;
import org.json.JSONArray;
import org.json.JSONException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.ArrayList;
import java.util.List;
import static Efectura.utilities.BrowserUtils.isElementDisplayed;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class SlkPages extends BasePage {

    @FindBy(xpath = "//div[2]/div/div/span/div/div[1]/div/input")
    public WebElement newrelicInputBox;

    @FindBy(xpath = "//span[contains(text(),'Query logs')]")
    public WebElement queryLogsButton;

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

    @FindBy(xpath = "//span[text()='New mail']")
    private WebElement outlookNewMailButton;

    @FindBy(xpath = "//div[@aria-label='To']")
    private WebElement outlookRecipientsInputBox;

    @FindBy(xpath = "(//span[text()='fatih.kara@efectura.com'])[1]")
    private WebElement karaMailOption;

    @FindBy(xpath = "//input[@placeholder='Add a subject']")
    private WebElement outlookMailSubjectInputBox;

    @FindBy(xpath = "//div[@aria-label='Message body, press Alt+F10 to exit']")
    private WebElement outlookMailMessageBodyInputBox;

    @FindBy(xpath = "//button[@title='Send (Ctrl+Enter)']")
    private WebElement outlookEmailSendButton;

    @FindBy(xpath = "//*[@id='O365_MainLink_MePhoto']/div/div/div/div/div[2]")
    private WebElement outlookLogoutProfile;

    @FindBy(xpath = "//a[text()='Oturumu kapat']")
    private WebElement outlookLogoutButton;


    //------------------------------------------------------------------------------
    @FindBy(xpath = "//span[contains(text(),'Efectura-QA')]")
    private WebElement efecturaMailGroup;

    @FindBy(xpath = "//span[contains(text(),'Deneme')]")
    private WebElement denemeMailGroup;

    @FindBy(xpath = "//span[contains(text(), 'QA Team')]")
    private WebElement qaMailGroup;

    @FindBy(xpath = "//span[contains(text(),'Gruplara Git')]")
    private WebElement goToGroupsButton;

    @FindBy(xpath = "//span[contains(text(),'E-posta gönder')]")
    private WebElement sendMailLink;
    //-----------------------------------------------------------------------------------

    @FindBy(xpath = "//input[@id='username']")
    private WebElement rancherUserNameInput;

    @FindBy(xpath = "//input[@type='password']")
    private WebElement rancherPasswordInput;

    @FindBy(xpath = "//button[@id='submit']")
    private WebElement rancherLoginBtn;

    @FindBy(xpath = "//span[@class='badge-state bg-success']")
    private List<WebElement> rancherSuccessStates;

    @FindBy(xpath = "//td[@data-title='Ready']")
    private List<WebElement> rancherReadyStates;

    @FindBy(xpath = "//span[text()='Download YAML']")
    private WebElement yamlElement;


    public void loginNewRelic() {
        Driver.getDriver().get("https://one.eu.newrelic.com/nr1-core?account=4144276&state=d7d8a30e-bff2-8c4b-0472-eb7573a4d579");
        BrowserUtils.waitForVisibility(emailNewRelic, 25);
        emailNewRelic.sendKeys(ConfigurationReader.getProperty("newRelicUserName"));
        submitNewRelic.click();
        passwordNewRelic.sendKeys(ConfigurationReader.getProperty("newRelicPassword"));
        submitNewRelic.click();
        BrowserUtils.wait(5);
        endNewRelic.click();
        BrowserUtils.wait(2);
        submitNewRelic.click();
        BrowserUtils.wait(4);
    }

    List<String> results = new ArrayList<>();
    public void verify(String service) {
        BrowserUtils.wait(3);
        if (!isElementDisplayed(noMatchingInfo)) {
            results.add(service + ": Error Number: " + errorInfoNewRelic.getText());
        } else {
            results.add(service + " : SUCCESS");
        }
        System.out.println(results);
    }

    List<String> resultsForRestarts = new ArrayList<>();
    public void verifyForRestarts(String restart) {
        BrowserUtils.wait(2);
        if (!isElementDisplayed(noMatchingInfo)) {
            resultsForRestarts.add(restart + ": Error Number: " + errorInfoNewRelic.getText());
        } else {
            resultsForRestarts.add(restart + " : SUCCESS");
        }
        System.out.println(resultsForRestarts);
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
        BrowserUtils.wait(2);
        outlookSubmitButton.click();
        BrowserUtils.wait(4);
    }

    public void sendMailForSlk() {
        List<String> emailRecipients = new ArrayList<>();
        emailRecipients.add("qateam2@groups.outlook.com");
//        emailRecipients.add("beyza.onal@efectura.com");
//        emailRecipients.add("semanur.gozuacik@efectura.com");
//        emailRecipients.add("ihsan.dinc@efectura.com");
//        emailRecipients.add("cem@efectura.com");
//        emailRecipients.add("adem.ciftci@efectura.com");
//        emailRecipients.add("cagdas.bakin@efectura.com");
//        emailRecipients.add("onur.coskun@efectura.com");
//        emailRecipients.add("fatih.kara@efectura.com");


        for (String recipient : emailRecipients) {
            BrowserUtils.wait(20);
            outlookNewMailButton.click();
            BrowserUtils.waitForVisibility(outlookRecipientsInputBox,30);
            outlookRecipientsInputBox.sendKeys(recipient);
            outlookMailSubjectInputBox.sendKeys("SLK Environment Controls");
            outlookMailMessageBodyInputBox.sendKeys(getEmailMessageBody());
            outlookMailMessageBodyInputBox.sendKeys("--------------------------------------------\n");
            BrowserUtils.wait(1);
            outlookMailMessageBodyInputBox.sendKeys(getEmailMessageBodyForRestarts());
            System.out.println(getEmailMessageBodyForRestarts());
            outlookEmailSendButton.click();
            BrowserUtils.wait(2);
        }
    }

    public void logoutMail() {
        outlookLogoutProfile.click();
        BrowserUtils.wait(4);
        outlookLogoutButton.click();
        BrowserUtils.wait(4);
    }

    public void sendGroupMailForSlk(Pages pages) {
//        denemeMailGroup.click();
//        efecturaMailGroup.click();
        pages.diaPages().getGoToGroupsButton().click();
//        goToGroupsButton.click();
        pages.diaPages().getQaMailGroup().click();
//        qaMailGroup.click();
        pages.diaPages().getSendMailLink().click();
//        sendMailLink.click();

        pages.diaPages().getOutlookMailSubjectInputBox().sendKeys("SLK Environment Controls");
//        outlookMailSubjectInputBox.sendKeys("SLK Environment Controls");
        pages.diaPages().getOutlookMailMessageBodyInputBox().sendKeys(getEmailMessageBody());
//        outlookMailMessageBodyInputBox.sendKeys(getEmailMessageBody());
        pages.diaPages().getOutlookMailMessageBodyInputBox().sendKeys("--------------------------------------------\n");
//        outlookMailMessageBodyInputBox.sendKeys("--------------------------------------------\n");
        BrowserUtils.wait(1);
        pages.diaPages().getOutlookMailMessageBodyInputBox().sendKeys(getEmailMessageBodyForRestarts());
//        outlookMailMessageBodyInputBox.sendKeys(getEmailMessageBodyForRestarts());
        System.out.println(getEmailMessageBodyForRestarts());
        pages.diaPages().getOutlookEmailSendButton().click();
//        outlookEmailSendButton.click();
        BrowserUtils.wait(2);
    }

    public void sendTelegramSmsForSlk() {
        String title = "SLK Environment Controls\n";
        String result = title + getEmailMessageBody() + "--------\n" +
                getEmailMessageBodyForRestarts() + "-------\n" + getAppsResponse + "\n---------\n" + rancherResult +
                "\n----------\n" + activeCampaignCount;

        BrowserUtils.sendMessageToTelegram(result,"-4194828120");
//        BrowserUtils.sendMessageToTelegram(result,"-1002156506449");
//        BrowserUtils.sendFileToTelegram(podsPath,"-1002156506449");
        BrowserUtils.sendFileToTelegram(podsPath,"-4194828120");
        BrowserUtils.deleteFileOrDirectory(System.getProperty("user.dir") + "\\test-output\\screenshots\\");

    }

    public String sendGetRequest(String urlString) {
        StringBuilder response = new StringBuilder();
        try {
            URL url = new URL(urlString);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Accept", "*/*");

            // Yanıtı oku
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
            return "Hata oluştu: " + e.getMessage();
        }
        return response.toString();
    }

    String getAppsResponse;
    public void getGetAppsResponse(String url) {
        getAppsResponse = sendGetRequest(url);
        getAppsResponse = getAppsResponse.replace(",", ",\n");
        getAppsResponse = getAppsResponse.replace("[", "[\n");
        getAppsResponse = getAppsResponse.replace("]", "\n]");

    }

    String rancherResult = "";
    String podsPath = "";
    public void lookForPods() {
        Driver.getDriver().get(ConfigurationReader.getProperty("rancherUrl"));
        BrowserUtils.wait(1);
        rancherUserNameInput.sendKeys(ConfigurationReader.getProperty("rancherUserName"));
        rancherPasswordInput.sendKeys(ConfigurationReader.getProperty("rancherPassword"));
        rancherLoginBtn.click();
        //----
        BrowserUtils.wait(2);
        Driver.getDriver().get("https://kube.silktech.ge/dashboard/c/local/explorer/pod");
        BrowserUtils.waitForVisibility(yamlElement,30);

        podsPath = BrowserUtils.getScreenshot("src/test/resources/data/rancher.png");

        boolean matchForSuccess = rancherSuccessStates.stream().allMatch(el -> el.getText().contains("Running"));
        boolean matchForReady = rancherReadyStates.stream().allMatch(el -> el.getText().contains("1/1"));

        if (matchForSuccess) {
            rancherResult += "All Pods is Running - SUCCESS";
        } else {
            rancherResult += "All Pods is not Running - FAIL";
        }

        if (matchForReady) {
            rancherResult += "\nAll services have 1 pod - SUCCESS";
        } else {
            rancherResult += "\nAll services doesn't have 1 pod - FAIL";
        }


    }

    String activeCampaignCount = "";
    public void getActiveCampaignCount(String itemType) throws JSONException {
        JSONArray jsonArray = BrowserUtils.getItemsByType(itemType);
        int count = jsonArray.length();
        System.out.println("Toplam aktif kampanya sayısı: " + count);
        activeCampaignCount = "Toplam aktif kampanya sayısı: " + count;
    }
}
