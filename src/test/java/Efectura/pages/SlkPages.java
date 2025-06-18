package Efectura.pages;

import Efectura.utilities.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static Efectura.utilities.BrowserUtils.isElementDisplayed;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class SlkPages extends BasePage {

    @FindBy(xpath = "//div/div[2]/div/div[1]/div/div/span/div/div[2]/div/input")
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
    private WebElement noMatchingInfo2;

    @FindBy(xpath = "//div[contains(@class,'wnd-EmptyState-content')]")
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
    private WebElement yamlElement2;

    @FindBy(xpath = "//span/span[contains(text(),'State')]")
    private WebElement yamlElement;


    public void loginNewRelic() {
        Driver.getDriver().get("https://one.eu.newrelic.com/nr1-core?account=4144276&state=d7d8a30e-bff2-8c4b-0472-eb7573a4d579");
        BrowserUtils.waitForVisibility(emailNewRelic, 25);
        emailNewRelic.sendKeys(ConfigurationReader.getProperty("newRelicUserName"));
        submitNewRelic.click();
        passwordNewRelic.sendKeys(ConfigurationReader.getProperty("newRelicPassword"));
//        BrowserUtils.adjustScreenSize(70,Driver.getDriver());
        submitNewRelic.click();
        BrowserUtils.wait(5);
        BrowserUtils.waitForVisibility(endNewRelic,30);
        endNewRelic.click();
        BrowserUtils.wait(2);
        submitNewRelic.click();
        BrowserUtils.wait(4);
    }

    List<String> results = new ArrayList<>();
    public void verify(String service) {
        BrowserUtils.wait(3);
//        BrowserUtils.adjustScreenSize(70,Driver.getDriver());
        if (!isElementDisplayed(noMatchingInfo)) {
            results.add(service + ": Error Number: " + errorInfoNewRelic.getText());
        } else {
            results.add(service + " : SUCCESS");
        }
        System.out.println(results);
    }

    List<String> resultsForRestarts = new ArrayList<>();
    public void verifyForRestarts(String restart) {
        BrowserUtils.wait(3);
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

    public void sendTelegramSmsForSlk() throws IOException {
        String title = "SLK Environment Controls\n";
        String result = title + getEmailMessageBody() + "----------------------\n" +
                getEmailMessageBodyForRestarts();
        String result2 = rancherResult + "\n---------------------------------------------\n" +
                activeCampaignCount + "\n---------------------------------------------\n" +
                totalEarningResult + "\n---------------------------------------------\n" +
                duplicateBirthdateResult + "\n---------------------------------------------\n" +
                multipleMembershipResult + "\n---------------------------------------------\n" +
                emptyMembershipAccountResult;

        String demoChatId = "-1002156506449";
        String actualChatId = "-4194828120";

        BrowserUtils.sendCodeBlockToTelegram(result,actualChatId);
        BrowserUtils.sendFileToTelegram(podsPath,actualChatId);
        BrowserUtils.sendCodeBlockToTelegram(result2,actualChatId);
        BrowserUtils.sendCodeBlockToTelegram(getAppsResponse,actualChatId);

//        BrowserUtils.sendCodeBlockToTelegram(result,demoChatId);
//        BrowserUtils.sendFileToTelegram(podsPath,demoChatId);
//        BrowserUtils.sendCodeBlockToTelegram(result2,demoChatId);
//        BrowserUtils.sendCodeBlockToTelegram(getAppsResponse,demoChatId);

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
        BrowserUtils.waitForVisibility(yamlElement,120);

        BrowserUtils.wait(1);
//        BrowserUtils.adjustScreenSize(50,driver);
        Driver.getDriver().manage().window().maximize();
        BrowserUtils.wait(1);
        podsPath = BrowserUtils.getFullPageScreenshot("rancher");
        System.out.println("podsPath --> " + podsPath);

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

    int duplicateBirthdateCount = 0;
    String duplicateBirthdateDetails;
    String duplicateBirthdateResult = "DuplicateBirthdateControl: ";
    public void getDuplicateBirthdates() {
        String query = """
                with cte as (select DeviceId,PointAttribute,UserId,YEAR(DateCallback) as yr, COUNT(1) as cnt from Callbacks
                WHERE callbackId = 15
                group by DeviceId,PointAttribute,UserId,YEAR(DateCallback)\s
                having count(1) >1)
                select DeviceId, yr as Year, cnt as EarnCount from cte""";

        String query2 = "select TOP 2 Id, phoneNumber,Used from UserReferrals";


        try (Connection conn = Database.getInstance();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {


            while (rs.next()) {
                duplicateBirthdateCount++;
            }
            System.out.println("duplicateBirthdateCount: " + duplicateBirthdateCount);

            duplicateBirthdateDetails = BrowserUtils.resultSetToTableString(rs);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (Connection conn = Database.getInstance();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            duplicateBirthdateDetails = BrowserUtils.resultSetToTableString(rs);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(duplicateBirthdateCount > 0) {
            duplicateBirthdateResult += "FAIL: \n" + duplicateBirthdateDetails;
        } else {
            duplicateBirthdateResult += "SUCCESS: QueryBoşDöndü";
        }
    }



    int multipleMembershipCount = 0;
    String multipleMembershipDetails;
    String multipleMembershipResult = "MultipleMembershipAssociation: ";
    public void getMultipleMembershipAssociates() {
        String query = """
                select SecondItemId FROM Associations
                WHERE AssociationTypeId = (SELECT Id FROM AssociationTypes where Code = 'MEMBERSHIP_ACCOUNT')
                group by SecondItemId
                having count(1)>1""";


        String query2 = "select TOP 2 Id, phoneNumber from UserReferrals";


        try (Connection conn = Database.getInstance();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)
        ) {

            while (rs.next()) {
                multipleMembershipCount++;
            }
            System.out.println("multipleMembershipCount: " + multipleMembershipCount);


        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (Connection conn = Database.getInstance();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            multipleMembershipDetails = BrowserUtils.resultSetToTableString(rs);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(multipleMembershipCount > 0) {
            multipleMembershipResult += "FAIL: \n" + multipleMembershipDetails;
        } else {
            multipleMembershipResult += "SUCCESS: QueryBoşDöndü";
        }


    }

    int totalEarning = 0;
    String totalEarningResult;
    public void getTotalCampaignEarning() {
        String query = """
                select SUM(Point) as PointTotal from Callbacks c
                join Items i ON i.Id = c.TaskId
                where DateCallback <= GETDATE() AND ItemStatusesId = 5""";



        try (Connection conn = Database.getInstance();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)
        ) {

            while (rs.next()) {
                totalEarning = rs.getInt("PointTotal");
            }
            System.out.println("totalEarning: " + totalEarning);
            totalEarningResult = "Total Campaign Earning : " + totalEarning;


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    //-------------------------------------------------------------------------------


    int emptyMembershipAccountCount = 0;
    String emptyMembershipAccountDetails;
    String emptyMembershipAccountResult = "EmptyMembershipAccountControl: ";
    public void getEmptyMembersihpAccounts() {
        String query = """
                SELECT\s
                                         i.Id AS AccountId,
                                                 a.FirstItemId as Membership,
                                                 i2.SKU AS Label
                                     FROM\s
                                         Items i
                                     LEFT JOIN\s
                                         ItemValues iv8 ON iv8.ItemId = i.Id
                                         AND iv8.AttributeId = (SELECT id FROM Attributes WHERE Code = 'AccountMembership')
                                     JOIN Associations a on a.SecondItemId =i.Id AND a.AssociationTypeId = (SELECT Id From AssociationTypes  WHERE Code = 'MEMBERSHIP_ACCOUNT')
                                         LEFT JOIN Items i2 ON i2.Id = a.FirstItemId
                                         LEFT JOIN Associations a2 ON a2.FirstItemId = i.Id AND a2.AssociationTypeId = (select Id from AssociationTypes where Code = 'ACCOUNT_MRP')
                                         LEFT JOIN ItemValues iv ON iv.ItemId = a2.SecondItemId AND iv.AttributeId = (select Id from Attributes where Code = 'UserChannel')
                                         LEFT JOIN AttributeOptions ao ON ao.Id = iv.ValueInt AND ao.AttributeId = (select Id from Attributes where Code = 'UserChannel')\s
                                     WHERE\s
                                         i.[Type] = 52\s
                                                 AND ao.Code LIKE '%SH%'
                                         AND iv8.ValueString IS NULL
                                         AND i.Id IN (SELECT Id FROM Items WHERE Type = 52 AND FamilyId = (SELECT Id FROM Families WHERE Code = 'ACCOUNT_TEST'))
                                        \s""";


        String query2 = "select TOP 2 Id, phoneNumber,Used from UserReferrals";


        try (Connection conn = Database.getInstance();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)
        ) {

            while (rs.next()) {
                emptyMembershipAccountCount++;
            }
            System.out.println("emptyMembershipAccountCount: " + emptyMembershipAccountCount);


        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (Connection conn = Database.getInstance();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            emptyMembershipAccountDetails = BrowserUtils.resultSetToTableString(rs);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(emptyMembershipAccountCount > 0) {
            emptyMembershipAccountResult += "FAIL: \n" + emptyMembershipAccountDetails;
        } else {
            emptyMembershipAccountResult += "SUCCESS: QueryBoşDöndü";
        }
    }

    public void checkDB() throws IOException {
        String excelPath = CommonExcelReader.getExcelPath("asd");
        String dbSKU = null;
        for (int i = 1; i <= 3527; i++) {
            dbSKU = null;
//            BrowserUtils.wait(1);
            String cellValue = CommonExcelReader.getCellValue(excelPath,"SKU",i);

            String query = "Select * From Items Where SKU = '" + cellValue.trim() + "'";

            try (Connection conn = Database.getInstance();
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(query)
            ) {

                while (rs.next()) {
                    dbSKU = rs.getString("SKU");
                }
                if (dbSKU == null) {
                    System.out.println(i + " - " +  cellValue + " -- " + dbSKU);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
