package Efectura.stepDefs;

import Efectura.utilities.BrowserUtils;
import Efectura.utilities.CommonExcelReader;
import Efectura.utilities.ConfigurationReader;
import Efectura.utilities.Driver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.json.JSONException;
import org.openqa.selenium.Keys;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SlkStepDefs extends BaseStep {

    @Given("The user login new relic")
    public void theUserLoginNewRelic() {
        pages.slkPages().loginNewRelic();
    }

    @Given("The user login SLK new relic page")
    public void theUserLoginSLKNewRelicPage() {
        List<String> slkServices = new ArrayList<>(Arrays.asList
                ("slkFletumApiWeb", "apiRegister","addCallback","CreateItem/EditItem",
                 "slkDBConnector", "slkCEPModule","slkItemService","slkOTPService",
                 "slkCDPService", "profileGet","getProfile","attributeSave","cdpRegister",
                 "slkApiGW", "apiGWgetItems",
                 "slkSisService","sisRegister","crmProducts","guestTransaction","applyGift",
                 "redeemVoucher","ticketApproval","sisGetItems","applyPromo","SaveCard","SaveCardWithOrderId"
                ));
        for (String service : slkServices) {
//            BrowserUtils.wait(5);
            Driver.getDriver().get(ConfigurationReader.getProperty(service));
            BrowserUtils.wait(12);
            pages.slkPages().verify(service);
        }
    }

    @Given("The user login SLK new relic for restart")
    public void theUserLoginSLKNewRelicForRestart() {
//        List<String> slkRestarts = new ArrayList<>(Arrays.asList("slkItemServiceRestart", "slkFletumApiWebRestart", "slkDBConnectorRestart", "slkCEPModuleRestart", "slkCDPServiceRestart", "slkApiGWRestart", "slkOTPServiceRestart", "slkSisServiceRestart" ));
//        //pages.cciPage().cciElasticFletumServices();
//        for (String restart : slkRestarts) {
////            BrowserUtils.wait(5);
//            Driver.getDriver().get(ConfigurationReader.getProperty(restart));
//            BrowserUtils.wait(9);
//            pages.slkPages().verifyForRestarts(restart);
//        }


        List<String> slkRestarts2 = new ArrayList<>(Arrays.asList
                ("slkItemServiceRestart", "slkFletumApiWebRestart", "slkDBConnectorRestart",
                        "slkCEPModuleRestart", "slkCDPServiceRestart", "slkApiGWRestart",
                        "slkOTPServiceRestart", "slkSisServiceRestart"
                ));
//        Driver.getDriver().get(ConfigurationReader.getProperty("cdpRegister"));
        for (String restart : slkRestarts2) {
//            BrowserUtils.wait(5);
//            pages.slkPages().newrelicInputBox.clear();
            BrowserUtils.wait(1);
            pages.slkPages().newrelicInputBox.sendKeys(Keys.CONTROL + "a");
            pages.slkPages().newrelicInputBox.sendKeys(Keys.DELETE);
//            BrowserUtils.wait(1);
            pages.slkPages().newrelicInputBox.sendKeys(ConfigurationReader.getProperty(restart));
            pages.slkPages().queryLogsButton.click();
//            BrowserUtils.wait(1);
            pages.slkPages().verifyForRestarts(restart);
        }

    }

    @When("user login to outlook")
    public void userLoginToOutlook() {
        pages.slkPages().loginOutlook();
    }

    @When("user sends the mail")
    public void userSendsTheMail() {
        pages.slkPages().sendMailForSlk();
    }

    @Given("user log out the mail")
    public void userLogOutTheMail() {
        pages.slkPages().logoutMail();
    }

    @Given("user send group mail for slk")
    public void userSendGroupMailForSlk() {
        pages.slkPages().sendGroupMailForSlk(pages);
    }

    @Given("The user looks SLK log errors")
    public void theUserLooksSLKLogErrors() {
//        BrowserUtils.adjustScreenSize(70,Driver.getDriver());
        List<String> slkServices = new ArrayList<>(Arrays.asList
                ("slkFletumApiWeb", "apiRegister","addCallback","CreateItem/EditItem",
                        "slkDBConnector", "slkCEPModule","slkItemService","slkOTPService",
                        "slkCDPService", "profileGet","getProfile","attributeSave","cdpRegister",
                        "slkApiGW", "apiGWgetItems",
                        "slkSisService","sisRegister","crmProducts","guestTransaction","applyGift",
                        "redeemVoucher","ticketApproval","sisGetItems","applyPromo","SaveCard","SaveCardWithOrderId",
                        "updatePlayer","sisAddCallback"
                ));
        Driver.getDriver().get(ConfigurationReader.getProperty("cdpRegister2"));
        for (String service : slkServices) {
//            BrowserUtils.wait(5);
//            pages.slkPages().newrelicInputBox.clear();
            BrowserUtils.wait(2);
            BrowserUtils.waitForVisibility(pages.slkPages().newrelicInputBox,60);
            pages.slkPages().newrelicInputBox.sendKeys(Keys.CONTROL + "a");
            pages.slkPages().newrelicInputBox.sendKeys(Keys.DELETE);
//            BrowserUtils.wait(1);
            pages.slkPages().newrelicInputBox.sendKeys(ConfigurationReader.getProperty(service));
            pages.slkPages().queryLogsButton.click();
//            BrowserUtils.wait(1);
            pages.slkPages().verify(service);
        }
    }

    @Given("The user sends telegram sms for slk")
    public void theUserSendsTelegramSmsForSlk() throws IOException {
        pages.slkPages().sendTelegramSmsForSlk();
    }

    @Given("The user gets GetApps response")
    public void theUserGetsGetAppsResponse() {
        pages.slkPages().getGetAppsResponse("https://fletum-cep-module-staging.silktech.ge/GetApps?isActive=true");
//        pages.slkPages().sendGetRequest("https://fletum-cep-module-staging.silktech.ge/GetApps?isActive=true");
    }

    @Given("The user looks for pods")
    public void theUserLooksForPods() {
        pages.slkPages().lookForPods();
    }

    @Given("The user gets active {string} count")
    public void theUserGetsActiveCount(String itemType) throws JSONException {
        pages.slkPages().getActiveCampaignCount(itemType);
    }

    @Given("The user gets birthday duplicates")
    public void theUserGetsBirthdayDuplicates() {
        pages.slkPages().getDuplicateBirthdates();
    }

    @Given("The user gets multiple membership associates")
    public void theUserGetsMultipleMembershipAssociates() {
        pages.slkPages().getMultipleMembershipAssociates();
    }

    @Given("The user gets total earnings value of campaigns")
    public void theUserGetsTotalEarningsValueOfCampaigns() {
        pages.slkPages().getTotalCampaignEarning();
    }

    @Given("The user gets empty membership items")
    public void theUserGetsEmptyMembershipItems() {
        pages.slkPages().getEmptyMembersihpAccounts();
    }

    @Given("Check")
    public void check() throws IOException {
        pages.slkPages().checkDB();
    }


    @And("The user send aply gift request for acc {string} gift {string} and venue {string}")
    public void theUserSendApllyGiftRequestForAccGiftAndVenue(String accountId, String giftItemId, String venueId) {
        BrowserUtils.sendApplyGiftRequest(accountId,giftItemId,venueId);
    }

    @Given("The user check logs")
    public void theUserCheckLogs() throws IOException {
        String excelPath = CommonExcelReader.getExcelPath("IBAN");

        Driver.getDriver().get("https://one.eu.newrelic.com/logger?account=4144276&begin=1746087600000&end=1748387280000&state=afdf1df6-df21-24c4-0971-f9d5203c05d2");
        for (int i = 1; i <= 273; i++) {
            String cellValue = CommonExcelReader.getCellValue(excelPath,"SKU",i);
            String actualIban = cellValue.substring(0, cellValue.length() - 3);
            String query = "entity.name:\"*sis*\" \"AUTHORIZATION\" \"" + actualIban + "\"";

            BrowserUtils.wait(10);
            BrowserUtils.waitForVisibility(pages.slkPages().newrelicInputBox,60);
            pages.slkPages().newrelicInputBox.sendKeys(Keys.CONTROL + "a");
            pages.slkPages().newrelicInputBox.sendKeys(Keys.DELETE);
//            BrowserUtils.wait(1);
            pages.slkPages().newrelicInputBox.sendKeys(query);
            pages.slkPages().queryLogsButton.click();
//            BrowserUtils.wait(1);
            pages.slkPages().verify(i + " " + cellValue);
        }
    }
}
