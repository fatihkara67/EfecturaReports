package Efectura.stepDefs;

import Efectura.utilities.BrowserUtils;
import Efectura.utilities.ConfigurationReader;
import Efectura.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

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
        List<String> slkServices = new ArrayList<>(Arrays.asList("slkItemService", "slkFletumApiWeb", "slkDBConnector", "slkCEPModule", "slkCDPService", "slkApiGW", "slkOTPService", "slkSisService"));
        for (String service : slkServices) {
//            BrowserUtils.wait(5);
            Driver.getDriver().get(ConfigurationReader.getProperty(service));
            BrowserUtils.wait(15);
            pages.slkPages().verify(service);
        }
    }

    @Given("The user login SLK new relic for restart")
    public void theUserLoginSLKNewRelicForRestart() {
        List<String> slkRestarts = new ArrayList<>(Arrays.asList("slkItemServiceRestart", "slkFletumApiWebRestart", "slkDBConnectorRestart", "slkCEPModuleRestart", "slkCDPServiceRestart", "slkApiGWRestart", "slkOTPServiceRestart", "slkSisServiceRestart" ));
        //pages.cciPage().cciElasticFletumServices();
        for (String restart : slkRestarts) {
//            BrowserUtils.wait(5);
            Driver.getDriver().get(ConfigurationReader.getProperty(restart));
            BrowserUtils.wait(9);
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
        pages.slkPages().sendGroupMailForSlk();
    }
}
