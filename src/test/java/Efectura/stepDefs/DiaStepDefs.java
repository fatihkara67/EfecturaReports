package Efectura.stepDefs;

import Efectura.pages.DiaPages;
import Efectura.utilities.BrowserUtils;
import Efectura.utilities.ConfigurationReader;
import Efectura.utilities.Database2;
import Efectura.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.json.JSONException;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static Efectura.utilities.BrowserUtils.isElementDisplayed;


public class DiaStepDefs extends BaseStep {

    @Given("The user login elastic")
    public void theUserLoginElastic() {
        pages.diaPages().loginElastic();
    }

    @Given("The user login elastic service")
    public void theUserLoginElasticService() {
        BrowserUtils.wait(7);
        List<String> services = new ArrayList<>(Arrays.asList("bpm", "itemService", "otpService", "dbConnector", "fletumApi", "fletumWeb", "diaService"));
        for (String service : services) {
            BrowserUtils.wait(2);
            Driver.getDriver().get(ConfigurationReader.getProperty(service));
            BrowserUtils.wait(5);

            pages.diaPages().verify(service);
        }
    }

    @Given("user go to mail")
    public void userGoToMail() {
        //Driver.getDriver().get("https://accounts.google.com/v3/signin/identifier?checkedDomains=youtube&continue=https%3A%2F%2Faccounts.google.com%2Fb%2F0%2FAddMailService&ddm=0&flowName=GlifWebSignIn&followup=https%3A%2F%2Faccounts.google.com%2Fb%2F0%2FAddMailService&hl=tr&ifkv=ARZ0qKL-_1mTeG5WHKYwFR48T4Ma88bYV1F8aMHtb6aI2zRRjtvLDuGGdlq2NAmKTECOwvmxIjaPpg&pstMsg=1&theme=mn&flowEntry=AccountChooser");
        Driver.getDriver().get("https://outlook.live.com/mail/0/");
        BrowserUtils.wait(12);
    }

    @When("user login email")
    public void userLoginEmail() {
        pages.diaPages().loginEmail();
    }

    @When("user click compose button")
    public void userClickComposeButton() {
        pages.diaPages().clickComposeButton();
    }

    @When("user enters mail credentials")
    public void userEntersMailCredentials() {
        pages.diaPages().enterEmailCredentials();
    }

    @Then("user clicks send button")
    public void userClicksSendButton() {
        pages.diaPages().clickSendButton();
    }

    @Given("The user login Fletum")
    public void theUserLoginFletum() {
        Driver.getDriver().get("https://diageo.efectura.com/Account/Login");
        pages.diaPages().loginFletum();
        BrowserUtils.wait(6);
    }

    @Given("The user impersonate Beyza")
    public void theUserImpersonateBeyza() {
        pages.diaPages().impersonateBeyza();
    }

    @Given("The user open BPM page")
    public void theUserOpenBPMPage() {
        Driver.getDriver().get("https://diageo.efectura.com/Task/TaskList");
        BrowserUtils.wait(6);
    }

    @Given("The user write {string} in search all filter")
    public void theUserWriteInSearchAllFilter(String searchFlow) {
        pages.diaPages().searchFor(searchFlow);
    }

    @Given("The user take the modul count")
    public void theUserTakeTheModulCount() {
        BrowserUtils.wait(9);
        pages.diaPages().takeModulCount();
    }

    @Given("The user take the menu count")
    public void theUserTakeTheMenuCount() {
        BrowserUtils.wait(9);
        pages.diaPages().takeMenuCount();
    }

    @When("user enters mail credentials for flow")
    public void userEntersMailCredentialsForFlow() {
        pages.diaPages().enterEmailCredentialsForFlow();
    }

    @Given("The user login tedarikci screen")
    public void theUserLoginTedarikciScreen() {
        pages.diaPages().tedarikciLogin();
    }


    @Given("Wait tedarik table")
    public void waitTedarikTable() {
        pages.diaPages().waitTedarikciTable();
    }

    @Given("The user sends email for dia")
    public void theUserSendsEmailForDia() {
        pages.diaPages().sendMailForDia();
    }

    //---------------------------------------------------------------
    @Given("The user sends group email for dia")
    public void theUserSendsGroupEmailForDia() {
        pages.diaPages().sendGroupEmailForDia();
    }

    @Given("The user sends telegram sms for dia")
    public void theUserSendsTelegramSmsForDia() {
        pages.diaPages().sendTelegramSmsForDia();
    }

    @Given("The user gets Advice Count")
    public void theUserGetsAdviceCount() {
        BrowserUtils.wait(2760);
        pages.diaPages().getAdviceCountt();
    }

    @Given("The user gets flow counts")
    public void theUserGetsFlowCounts() throws JSONException {
        pages.diaPages().getFlowCounts();
    }
}
