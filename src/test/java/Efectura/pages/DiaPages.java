package Efectura.pages;

import Efectura.utilities.*;
import lombok.Getter;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static Efectura.utilities.BrowserUtils.isElementDisplayed;


@Getter
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

    @FindBy(xpath = "//span[text()='New mail']")
    private WebElement outlookNewMailButton;

    @FindBy(xpath = "//div[@aria-label='To']")
    private WebElement outlookRecipientsInputBox;

    @FindBy(xpath = "(//span[text()='fatih.kara@efectura.com'])[1]")
    private WebElement karaMailOption;

    @FindBy(xpath = "(//span[text()='cem@efectura.com'])[1]")
    private WebElement cemMailOption;

    @FindBy(xpath = "(//span[text()='emre.kurt@efectura.com'])[1]")
    private WebElement emreMailOption;

    @FindBy(xpath = "//input[@placeholder='Add a subject']")
    private WebElement outlookMailSubjectInputBox;

    @FindBy(xpath = "//div[@aria-label='Message body, press Alt+F10 to exit']")
    private WebElement outlookMailMessageBodyInputBox;

    @FindBy(xpath = "//button[@title='Send (Ctrl+Enter)']")
    private WebElement outlookEmailSendButton;

    @FindBy(xpath = "//span[contains(text(),'Impersonate')]")
    private WebElement impersonateButton;

    @FindBy(xpath = "//a[@id='impersonate-fletum']")
    private WebElement impersonateFletumButton;

    @FindBy(xpath = "//button[contains(.,'Refresh')]")
    private WebElement refreshButton;

    //------------------------------------------------------------------------------
    @FindBy(xpath = "//span[contains(text(),'Efectura-QA')]")
    private WebElement efecturaMailGroup;

    @FindBy(xpath = "//span[contains(text(),'Deneme')]")
    private WebElement denemeMailGroup;

    @FindBy(xpath = "//div/div/div/div[3]/div/div/div/div/span[starts-with(text(),'QA Team')]")
    private WebElement qaMailGroup;

    @FindBy(xpath = "//span[contains(text(),'Go to Groups')]")
    private WebElement goToGroupsButton;

    @FindBy(xpath = "//span[contains(text(),'Send email')]")
    private WebElement sendMailLink;

    @FindBy(xpath = "//p[contains(text(),\"We couldn't log you in. Please try again.\")]")
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
        BrowserUtils.adjustScreenSize(70,Driver.getDriver());
        BrowserUtils.wait(1);
        if (!isElementDisplayed(noMatchingInfo)) {
            results.add(service + ": Error Number: " + numberOfFilteredData.getText());
        } else {
            results.add(service + " : SUCCESS");
            Assert.assertTrue(noMatchingInfo.isDisplayed());
        }

    }

    public void verifyNotErrorLogs(String service) {
        BrowserUtils.adjustScreenSize(70,Driver.getDriver());
        BrowserUtils.wait(1);
        if (!isElementDisplayed(noMatchingInfo)) {
            results.add(service + ": LogCount: " + numberOfFilteredData.getText() + " SUCCESS");
        } else {
            results.add(service + " NO LOG : FAIL");
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
        BrowserUtils.wait(2);
        searchFilterFlow.sendKeys(searchFlow);
        BrowserUtils.wait(3);
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
        return "Modül Sayısı: " + modulCount + "\n" + "Menü Sayısı: " + menuCount + "\n" + result +
                "\n" + getAdviceCount() + "\n" + oneriSiparis + "\n" + oneriSiparisResults;
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
//        BrowserUtils.captureScreenshot(driver,"src/test/resources/data/screenshot2.png");
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
        emailRecipients.add("qateam2@groups.outlook.com");
//        emailRecipients.add("ihsan.dinc@efectura.com");
//        emailRecipients.add("semanur.gozuacik@efectura.com");
//        emailRecipients.add("cem@efectura.com");
//        emailRecipients.add("adem.ciftci@efectura.com");
//        emailRecipients.add("cagdas.bakin@efectura.com");
//        emailRecipients.add("onur.coskun@efectura.com");
//        emailRecipients.add("beyza.onal@efectura.com");
//        emailRecipients.add("fatih.kara@efectura.com");


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
        efecturaMailGroup.click();
        denemeMailGroup.click();

//        EmailSender.sendEmail("fatih.kara@efectura.com", "Deneme","Deneme");

        BrowserUtils.wait(3);
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
        emailRecipients.add("qateam2@groups.outlook.com");
//        emailRecipients.add("ihsan.dinc@efectura.com");
//        emailRecipients.add("semanur.gozuacik@efectura.com");
//        emailRecipients.add("cem@efectura.com");
//        emailRecipients.add("adem.ciftci@efectura.com");
//        emailRecipients.add("cagdas.bakin@efectura.com");
//        emailRecipients.add("onur.coskun@efectura.com");
//        emailRecipients.add("beyza.onal@efectura.com");
//        emailRecipients.add("fatih.kara@efectura.com");


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
        BrowserUtils.wait(3);
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

    public void sendTelgramSmsForMm() {
        String result = "MM Service Controls\n" + getEmailMessageBody();
        BrowserUtils.sendMessageToTelegram(result,"-4194828120");
//        BrowserUtils.sendMessageToTelegram(result,"-1002156506449");
//        BrowserUtils.sendMessageToTelegram("MM Service Controls","-4194828120");
//        BrowserUtils.sendMessageToTelegram(getEmailMessageBody(),"-4194828120");

    }

    public void sendTelegramSmsForDia() {
        String result = "Environment Elastic, Flows And Tedarik Screen Control\n"
                + getEmailMessageBody() + "------------------------\n" + getEmailMessageBodyForFlow();
        BrowserUtils.sendMessageToTelegram(result,"-4194828120");
//        BrowserUtils.sendMessageToTelegram(result,"-1002156506449");
    }


    public static String getAdviceCount() {
        String query = """
                SELECT Baslangictarihi, COUNT(DISTINCT Musterikriter) as AdviceCount
                                FROM my_database.OneriSiparis
                                GROUP BY Baslangictarihi
                                ORDER BY Baslangictarihi DESC
                                LIMIT 1""";


        String adviceCounts = "";
        try (Connection conn = Database2.getInstance();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                // İlk kolondaki tarih bilgisini al
                String baslangicTarihi = rs.getString("Baslangictarihi");

                // İkinci kolondaki sayıyı al
                int countValue = rs.getInt("AdviceCount");

                // Sonucu "tarih --- sayı" formatında ekle
                adviceCounts = "Öneri Sayıları: " + baslangicTarihi.split(" ")[0] + " --- " + countValue;
//                adviceCounts.append(baslangicTarihi).append(" --- ").append(countValue).append("\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Öneri Sayıları:\n" + adviceCounts);
        return adviceCounts;
    }

    public void getAdviceCountt() {
        getAdviceCount();
    }

    public static String getFlowCount(String flowName) throws JSONException {
        JSONObject json = BrowserUtils.getRecordsFiltered(flowName," .AspNet.ApplicationCookie=EhC-da6Y6O4Np-Hc8f6ZhBpU6_lyBzYkeOf6FuzS0BfjZux_cUuOM-9jYKQNGuPYUgAvNAA-VDpG_EkRfC-2H_oqL0kN4iqHCvjn3KjVL4evYZz8Mq6I3y7jaqN0WCeiq6IhtHm1hcsIt0yCeYt5mCHayk8B5tt-x8JsmTvlPKxZyKpMLRzV2r_P_cPG-usH-ujSLYZfsk9M4U-jB77GcqSMGTWbUOwrts55g6M-j1PUSLzQ-5jgH8neCNQognMsstlgWMynbzllNQWRw1Sx05_pwA2fiula0Oml5JjjM6byIkzxBsIRP7bSQwtTk-0wZi_TzT0ehpPhSYUjk371dQcCQ6k9WcB4h0vdPK9ArHvtVWAhEGDXpXvupEd6cTCuv8SdcVlmU9fFv7oO8W7iyeFyIgjVmln5LwcLSrGTL5goZb9y8XGeO9Lwg5k9feoQluu9-sVA0JuLIBXRpLXY3LiLcndLi-wrPZnSSXsz17GT1vP42ULoHnDE1-McEbONUNWcUAVK33ktaWVtvdDM60OUcLkBqux6jftk_AAvWjXPvA74Jgj2AfZGuo3zwXnAblcyQBq3etWqkcH-h3uDWbth4ayJEzavgHKeeoYVLfe8JKdIl6g-HsXi5YmDaLMkFjTQXSVuOGaXpm4EmMsFimlHTLozsPV80DGVt2BsAkou_80pCodaZEb-tQEGqY_XOyyaFOxfTBdnpt-OCr8UwqUgV_kFb0inZ3NUvl62bXJ1jdpq6-LDe7mZtoNSgjto5Okw2YM4XIegk65GIhSUDKnVN4L93GAfXR_umavCx9OllK5CBPjaldobrxpRKZP-89Dke6JOpF3_3YXhmboymTXTtblfqrfGxguuwDl6e2tpM0nJb53K4a-5nAi3qNWDg1UZ1-gY6QFE1Kb1t4YMsz2nDm21KH3GUm5xGV7F5ZQvnPv5XHYLsTvEjMGTJQRgX6CVgERUXs5L3ScRKp1dLwprd0BvjzMHo5AHIGCg2QGQv_zW1E5k4mghcAgGijBWmUG8CSnG806SgNbLqK-IxzbK1v8Fn3qCpF0OgOO3-87swjftJT_IQgFcClwYzXBaGwYMnA3leI6ihAEQObMMWa5CN1sdR9sNz1CQbA5GmO8elyKruHXOMnvmEGulOwO8wN61XGuGxUF9Gnv8IejqRVxasR2GpOlemrbgvdoLheJCxU7IlqVzoFemskXr8a5WwqSizyAVkBXmOky56C02wJypd1eTzHAvTaODuuc1SPrto-X1zBWkalGiUGHnQjNaII8VpEi9mfmTgqA3tICasz88FOKAd5Pi-kC97_J-oVvrbcBKT6Tb-TBItL4ZaOm_sPxvmq0BVUHLRHrQyzN50OrbkC4vYdh3JqB0dcRb47FtNcgloSieWcarCYr8Zmj4uxgoJjOt_apNGpjVTq4Eq82oZKjxD0g-G5Lng_BF7SMEqPG18thzC3iWAlItC6zvpY71SOrPkGhE2DCPgwkLfg6iYnmz2MB7mg4X2RgHtZ2c1iPmuAiLSyHhjwsu5weG8AbCfYrnkQgB_TqYOCc5R-awDQFl9UGkUZNhvSqR-FU; UserId=728d9a80-33d7-4b7d-898e-ef5fe83cb211");

        assert json != null;
        return json.getString("recordsFiltered");
    }

    public void getFlowCounts() throws JSONException {
        modulCount = getFlowCount("MODUL");
        menuCount = getFlowCount("MENU");


    }

    public void setMessage() {
        results.add("Elastic'e Giriş Yapılamadı");
    }

    String oneriSiparis = "";
    public String getOneriSiparisDate() {
        String query = """
                select toString(Referans) as Referans,Aciklama,DistKod,Musterikritertip,Musterikriter,Baslangictarihi,Bitistarihi,Durum,Urunkodu,UrunReferans,Miktar,Birim\s
                FROM my_database.OneriSiparis
                WHERE Musterikriter IN (select distinct TXTERPKOD from my_database.staging_account_dummy WHERE LNGKOD IN (select distinct LNGMUSTERIKOD from my_database.planned_visits where PLANLANAN_ZIYARET_TARIHI = today()) and (SON_ROUTE_ADI like '%Telesell%' or SON_ROUTE_ID IN (2129,2763,1738)) and BYTDURUM = 0)\s
                AND Baslangictarihi >= today()
                """;


        try (Connection conn = Database2.getInstance();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            int count = 0;
            while (rs.next()) {
                count++;
                // İlk kolondaki tarih bilgisini al
                String baslangicTarihi = rs.getString("Baslangictarihi");

                // Sonucu "tarih --- sayı" formatında ekle
                oneriSiparis = "Öneri Sipariş: " + baslangicTarihi.split(" ")[0] + " --- " + count + " kayıt var";
//                adviceCounts.append(baslangicTarihi).append(" --- ").append(countValue).append("\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Öneri Sipariş:\n" + oneriSiparis);
        return oneriSiparis;
    }

    String oneriSiparisResults;
    public String getOneriSiparisResults() {
        String query = """
                select * from my_database.OneriSiparis_Results osr
                where osr.StartDate >= today()
                """;

        try (Connection conn = Database2.getInstance();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            int count = 0;
            while (rs.next()) {
                count++;
                String baslangicTarihi = rs.getString("StartDate");

                // Sonucu "tarih --- sayı" formatında ekle
                oneriSiparisResults = "OneriSiparis_Results: " + baslangicTarihi.split(" ")[0] + " --- " + count + " kayıt var";
//                adviceCounts.append(baslangicTarihi).append(" --- ").append(countValue).append("\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("OneriSiparis_Results:\n" + oneriSiparisResults);
        return oneriSiparisResults;

    }
}
