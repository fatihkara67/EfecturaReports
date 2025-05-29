package Efectura.stepDefs;

import Efectura.utilities.BrowserUtils;
import Efectura.utilities.ConfigurationReader;
import Efectura.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.json.JSONException;
import org.openqa.selenium.Keys;

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
        List<String> slkServices = new ArrayList<>(Arrays.asList
                ("slkFletumApiWeb", "apiRegister","addCallback","CreateItem/EditItem",
                        "slkDBConnector", "slkCEPModule","slkItemService","slkOTPService",
                        "slkCDPService", "profileGet","getProfile","attributeSave","cdpRegister",
                        "slkApiGW", "apiGWgetItems",
                        "slkSisService","sisRegister","crmProducts","guestTransaction","applyGift",
                        "redeemVoucher","ticketApproval","sisGetItems","applyPromo","SaveCard","SaveCardWithOrderId"
                ));
        Driver.getDriver().get(ConfigurationReader.getProperty("cdpRegister2"));
        for (String service : slkServices) {
//            BrowserUtils.wait(5);
//            pages.slkPages().newrelicInputBox.clear();
            BrowserUtils.wait(3);
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
    public void theUserSendsTelegramSmsForSlk() {
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
}
